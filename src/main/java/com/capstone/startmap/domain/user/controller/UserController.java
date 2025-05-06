package com.capstone.startmap.domain.user.controller;

import com.capstone.startmap.config.CustomUserDetails;
import com.capstone.startmap.config.security.jwt.JwtAuthenticationFilter;
import com.capstone.startmap.domain.refreshtoken.dto.request.CreateRefreshTokenRequest;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.dto.request.CreateUserRequest;
import com.capstone.startmap.domain.user.dto.request.DeleteUserRequest;
import com.capstone.startmap.domain.user.dto.request.LoginUserRequest;
import com.capstone.startmap.domain.user.dto.response.CreateUserResponse;
import com.capstone.startmap.domain.user.dto.response.LoginUserResponse;
import com.capstone.startmap.domain.user.service.UserService;
import com.capstone.startmap.util.validation.EmailGroup;
import com.capstone.startmap.util.validation.PasswordGroup;
import com.capstone.startmap.util.validation.RequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;
    private final RequestValidator requestValidator;

    @Operation(summary = "자체 회원가입", description = "카카오 에러 고치다가 만든 자체 회원가입입니다.. 꼭 사용 안하셔도 됩니다")
    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> signUp(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse response = userService.signUp(request.getEmail(), request.getPassword(), request.getNickname(), request.getKakaoId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "로그인", description = "자체로그인시 email/pw 그대로 입력, 카카오 로그인 시 email: 카카오로그인 시 회원Id/pw: startmapINUCapstoneOauthLoginPW(고정값)")
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        LoginUserResponse response = userService.login(request.getEmail(), request.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + response.getAccessToken());

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

    @Operation(summary = "리프레쉬", description = "리프레쉬 토큰 발급")
    @PostMapping("/refresh")
    public ResponseEntity<LoginUserResponse> refresh(@RequestBody CreateRefreshTokenRequest request) {
        LoginUserResponse response = userService.refresh(request);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody CreateRefreshTokenRequest request) {
        boolean deleted = userService.deleteByToken(request.getRefreshToken());

        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh Token -> 존재하지 않는 토큰이거나 만료된 토큰입니다.");
        }
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody DeleteUserRequest request,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        Optional<User> findUser = userService.findUserById(userDetails.getId());
        User user = findUser.get();

        if (user.getKakao_id() != null && !user.getKakao_id().isEmpty()) {
            requestValidator.validate(request, EmailGroup.class);  // EmailGroup 검증 수행
            userService.deleteUserByKakaoId(request.getKakao_id(), userDetails.getId());

            return ResponseEntity.ok("회원 탈퇴가 처리되었습니다.");

        } else if (user.getKakao_id() == null) {
            requestValidator.validate(request, PasswordGroup.class);  // PasswordGroup 검증 수행
            userService.deleteUserByPassword(request.getPassword(), userDetails.getId());

            return ResponseEntity.ok("회원 탈퇴가 처리되었습니다.");

        } else {
            return ResponseEntity.badRequest().body("필수 입력 사항이 누락되었습니다.");
        }
    }
}
