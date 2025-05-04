package com.capstone.startmap.domain.user.service;

import com.capstone.startmap.domain.user.dto.response.LoginUserResponse;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.dto.oauth.KakaoProfile;
import com.capstone.startmap.domain.user.dto.oauth.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final UserService userService;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kakao.password}")
    private String password;

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    public OAuthToken getKakaoOAuthToken(String code) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                String.class
        );
        return objectMapper.readValue(response.getBody(), OAuthToken.class);
    }

    public KakaoProfile getKakaoProfile(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                profileRequest,
                String.class
        );
        return objectMapper.readValue(response.getBody(), KakaoProfile.class);
    }

    public LoginUserResponse registerOrLoginUser(KakaoProfile profile) {
        String email = "KAKAO_" + profile.getId();
        System.out.println("이메일"+email);
        Optional<User> foundMember = userService.findUserByEmail(email);

        if (foundMember.isEmpty()) {
            String nickname = "회원" + UUID.randomUUID().toString().replace("-", "");
            userService.signUp(email, password, nickname, profile.getKakao_account().getEmail());
        }

        return userService.login(email, password);
    }
}