package com.curady.userservice.domain.auth.oauthRequest;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OAuthRequestFactory {

    private final KakaoInfo kakaoInfo;
    private final GoogleInfo googleInfo;

    public OAuthRequest getRequest(String code, String provider) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        if (provider.equals("kakao")) {
            map.add("grant_type", "authorization_code");
            map.add("client_id", kakaoInfo.getKakaoClientId());
            map.add("redirect_uri", kakaoInfo.getKakaoRedirect());
            map.add("code", code);

            return new OAuthRequest(kakaoInfo.getKakaoTokenUrl(), map);

        } else {
            map.add("grant_type", "authorization_code");
            map.add("client_id", googleInfo.getGoogleClientId());
            map.add("client_secret", googleInfo.getGoogleClientSecret());
            map.add("redirect_uri", googleInfo.getGoogleRedirect());
            map.add("code", java.net.URLDecoder.decode(code, StandardCharsets.UTF_8));

            return new OAuthRequest(googleInfo.getGoogleTokenUrl(), map);
        }
    }

    public String getProfileUrl(String provider) {
        if (provider.equals("kakao")) {
            return kakaoInfo.getKakaoProfileUrl();
        } else {
            return googleInfo.getGoogleProfileUrl();
        }
//        else if (provider.equals("naver")){
//            return naverInfo.getNaverProfileUrl();
//        }
    }

    @Getter
    @Component
    static class KakaoInfo {
        @Value("${spring.social.kakao.client_id}")
        String kakaoClientId;
        @Value("${spring.social.kakao.redirect}")
        String kakaoRedirect;
        @Value("${spring.social.kakao.url.token}")
        private String kakaoTokenUrl;
        @Value("${spring.social.kakao.url.profile}")
        private String kakaoProfileUrl;
    }

    @Getter
    @Component
    static class GoogleInfo {
        @Value("${spring.social.google.client_id}")
        String googleClientId;
        @Value("${spring.social.google.redirect}")
        String googleRedirect;
        @Value("${spring.social.google.client_secret}")
        String googleClientSecret;
        @Value("${spring.social.google.url.token}")
        private String googleTokenUrl;
        @Value("${spring.social.google.url.profile}")
        private String googleProfileUrl;
    }
}