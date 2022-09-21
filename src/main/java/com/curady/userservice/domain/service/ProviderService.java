package com.curady.userservice.domain.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.curady.userservice.advice.exception.CommunicationException;
import com.curady.userservice.domain.auth.profile.KakaoProfile;
import com.curady.userservice.domain.auth.AccessToken;
import com.curady.userservice.domain.auth.oauthRequest.OAuthRequest;
import com.curady.userservice.domain.auth.oauthRequest.OAuthRequestFactory;
import com.curady.userservice.domain.auth.profile.ProfileDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final RestTemplate restTemplate;
    private final Gson gson;
    private final OAuthRequestFactory oAuthRequestFactory;

    public ProfileDto getProfile(String accessToken, String provider) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        log.info(String.valueOf(httpHeaders));

        String profileUrl = oAuthRequestFactory.getProfileUrl(provider);

        log.info(profileUrl);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, httpHeaders);

        log.info(String.valueOf(request));
        ResponseEntity<String> response = restTemplate.postForEntity(profileUrl, request, String.class);
        log.info(String.valueOf(response));
        log.info(String.valueOf(response.getStatusCode()));
        log.info(String.valueOf(response.getStatusCodeValue()));
        log.info(String.valueOf(HttpStatus.OK));
        try {
            if (response.getStatusCode() == HttpStatus.OK) {
                return extractProfile(response, provider);
            }
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }

    private ProfileDto extractProfile(ResponseEntity<String> response, String provider) {
        log.info(response.getBody());
        log.info(String.valueOf(KakaoProfile.class));
        KakaoProfile kakaoProfile = gson.fromJson(response.getBody(), KakaoProfile.class);
        log.info(String.valueOf(kakaoProfile));
        return new ProfileDto(kakaoProfile.getKakao_account().getEmail());
    }

    public AccessToken getAccessToken(String code, String provider) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        OAuthRequest oAuthRequest = oAuthRequestFactory.getRequest(code, provider);
        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(oAuthRequest.getMap(), httpHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity(oAuthRequest.getUrl(), request, String.class);
        try {
            if (response.getStatusCode() == HttpStatus.OK) {
                return gson.fromJson(response.getBody(), AccessToken.class);
            }
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }
}