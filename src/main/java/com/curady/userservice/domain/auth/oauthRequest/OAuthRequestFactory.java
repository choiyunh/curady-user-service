package com.curady.userservice.domain.auth.oauthRequest;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

@Component
@RequiredArgsConstructor
public class OAuthRequestFactory {

    private final KakaoInfo kakaoInfo;

    public OAuthRequest getRequest(String code, String provider) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", kakaoInfo.getKakaoClientId());
        map.add("redirect_uri", kakaoInfo.getKakaoRedirect());
        map.add("code", code);

        return new OAuthRequest(kakaoInfo.getKakaoTokenUrl(), map);
    }

    public String getProfileUrl(String provider) {
        return kakaoInfo.getKakaoProfileUrl();
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
}