package com.capstone.startmap.domain.user.service;

import com.capstone.startmap.domain.refreshtoken.RefreshToken;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.repository.UserRepository;
import com.capstone.startmap.domain.refreshtoken.repository.RefreshTokenRepository;
import com.capstone.startmap.domain.refreshtoken.dto.request.CreateRefreshTokenRequest;
import com.capstone.startmap.domain.user.dto.response.CreateUserResponse;
import com.capstone.startmap.domain.user.dto.response.LoginUserResponse;
import com.capstone.startmap.config.security.jwt.JwtTokenProvider;
import com.capstone.startmap.exception.user.DuplicatedEmailException;
import com.capstone.startmap.exception.user.DuplicatedNicknameException;
import com.capstone.startmap.exception.user.WrongPasswordException;
import lombok.RequiredArgsConstructor;
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
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;

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

        RefreshToken refreshEntity = new RefreshToken(refresh, authentication.getName());
        refreshTokenRepository.save(refreshEntity);

        return new LoginUserResponse(authentication.getName(), user.getNickname(), access, refresh, accessTokenExpire);
    }

    @Transactional
    public CreateUserResponse signUp(String email, String password, String nickname) {

        userRepository.findByEmail(email).ifPresent(it -> {
            throw new DuplicatedEmailException();
        });

        userRepository.findByNickname(nickname).ifPresent(it -> {
            throw new DuplicatedNicknameException();
        });

        User user = userRepository.save(User.of(nickname, email, passwordEncoder.encode(password)));

        return new CreateUserResponse(user.getUser_id(), user.getEmail(), user.getNickname());
    }

    //액세스 토큰 재발급 로직
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
        /*Optional<RefreshToken> refresh = refreshTokenRepository.findByRefreshToken(refreshToken);

        if (refresh.isPresent()) {
            refreshTokenRepository.deleteByRefreshToken(refreshToken);
            return true;
        } else {
            return false;
        }*/
    }

}