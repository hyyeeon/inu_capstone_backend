package com.capstone.startmap.domain.user.controller;

import com.capstone.startmap.config.security.jwt.JwtAuthenticationFilter;
import com.capstone.startmap.domain.user.dto.oauth.KakaoProfile;
import com.capstone.startmap.domain.user.dto.oauth.OAuthToken;
import com.capstone.startmap.domain.user.dto.response.LoginUserResponse;
import com.capstone.startmap.domain.user.service.OauthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kakao")
@Tag(name = "Kakao", description = "Kakao API")
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/callback")
    @Operation(summary = "카카오 로그인 콜백", description = "자체 호출 함수로 사용하실 일이 없을 거예요")
    public ResponseEntity<LoginUserResponse> callback(@RequestParam String code) throws JsonProcessingException {
        OAuthToken token = oauthService.getKakaoOAuthToken(code);
        KakaoProfile profile = oauthService.getKakaoProfile(token.getAccess_token());
        LoginUserResponse response = oauthService.registerOrLoginUser(profile);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + response.getAccessToken());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

