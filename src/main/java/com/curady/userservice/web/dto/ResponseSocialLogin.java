package com.curady.userservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSocialLogin {
    private String email;
    private String nickname;
    private boolean isFirst;
    private String token;
    private String refreshToken;
}