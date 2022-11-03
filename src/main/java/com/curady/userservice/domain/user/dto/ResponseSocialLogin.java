package com.curady.userservice.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSocialLogin {
    private String email;
    private Long id;
    private String nickname;
    private Boolean isFirst;
    private String token;
    private String refreshToken;
}