package com.capstone.startmap.domain.user.service;

import com.capstone.startmap.domain.refreshtoken.RefreshToken;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.events.UserDeletedEvent;
import com.capstone.startmap.domain.user.repository.UserRepository;
import com.capstone.startmap.domain.refreshtoken.repository.RefreshTokenRepository;
import com.capstone.startmap.domain.refreshtoken.dto.request.CreateRefreshTokenRequest;
import com.capstone.startmap.domain.user.dto.response.CreateUserResponse;
import com.capstone.startmap.domain.user.dto.response.LoginUserResponse;
import com.capstone.startmap.config.security.jwt.JwtTokenProvider;
import com.capstone.startmap.exception.user.DuplicatedEmailException;
import com.capstone.startmap.exception.user.DuplicatedNicknameException;
import com.capstone.startmap.exception.user.NotFoundUserException;
import com.capstone.startmap.exception.user.EmailMismatchException;
import com.capstone.startmap.exception.user.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public LoginUserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("존재하지 않는 회원입니다."));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        //시큐리티 컨텍스트에 인증정보 저장
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String access = tokenProvider.createAccessToken(authentication);
        String refresh = tokenProvider.createRefreshToken(authentication);
        Long accessTokenExpire = tokenProvider.getAccessTokenExpire(access).getTime();

        System.out.println("authentication.getName(): " + authentication.getName());
        System.out.println("authentication.getPrincipal(): " + authentication.getPrincipal());

        RefreshToken refreshEntity = new RefreshToken(refresh, authentication.getName(), user);
        //바로 윗 줄의 authentication.getName()이 null이야
        refreshTokenRepository.save(refreshEntity);

        return new LoginUserResponse(authentication.getName(), user.getNickname(), access, refresh, accessTokenExpire);
    }

    @Transactional
    public CreateUserResponse signUp(String email, String password, String nickname, String kakaoId) {

        userRepository.findByEmail(email).ifPresent(it -> {
            throw new DuplicatedEmailException();
        });

        userRepository.findByNickname(nickname).ifPresent(it -> {
            throw new DuplicatedNicknameException();
        });

        password = passwordEncoder.encode(password);
        User user;

        if (email.startsWith("KAKAO_") && nickname.startsWith("회원")) {
            user = userRepository.save(User.of(nickname, email, password, kakaoId));
        } else {
            user = userRepository.save(User.of(nickname, email, password, null));
        }
        System.out.println("\\\\\\회원가입 닉넴 이메일 pw, kkoid"+nickname+" "+email+" "+password+" "+kakaoId);

        return new CreateUserResponse(user.getUser_id(), user.getKakao_id(), user.getEmail(), user.getEmail());
    }

    //액세스 토큰 재발급 로직
    @Transactional(readOnly = true)
    public LoginUserResponse refresh(CreateRefreshTokenRequest request) {
        try {
            tokenProvider.validateToken(request.getRefreshToken());
            Optional<RefreshToken> refresh = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());

            if (refresh.isPresent() && request.getRefreshToken().equals(refresh.get().getRefreshToken())) {
                String email = refresh.get().getEmail();
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                User user = userRepository.findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String newAccessToken = tokenProvider.createAccessToken(authentication);
                Long accessTokenExpire = tokenProvider.getAccessTokenExpire(newAccessToken).getTime();

                return new LoginUserResponse(userDetails.getUsername(), user.getNickname(), newAccessToken, request.getRefreshToken(), accessTokenExpire);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레쉬 토큰입니다.");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "리프레쉬 토큰 생성 실패", e);
        }
    }

    @Transactional
    public boolean deleteByToken(String refreshToken) {
        int count = refreshTokenRepository.deleteByRefreshToken(refreshToken);
        return count > 0;
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //카카오 로그인 사용자의 회원 탈퇴
    @Transactional
    public void deleteUserByKakaoId(String kakaoId, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundUserException();
        }

        User user = userOptional.get();

        if (user.getKakao_id().equalsIgnoreCase(kakaoId)) {
            eventPublisher.publishEvent(new UserDeletedEvent(this, user));
            userRepository.deleteById(id);
        } else {
            throw new EmailMismatchException();
        }
    }

    //일반 가입 사용자의 회원 탈퇴
    @Transactional
    public void deleteUserByPassword(String password, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundUserException();
        }

        User user = userOptional.get();


        if (passwordEncoder.matches(password, user.getPassword())) {
            eventPublisher.publishEvent(new UserDeletedEvent(this, user));
            userRepository.deleteById(id);
        } else {
            throw new WrongPasswordException();
        }
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
