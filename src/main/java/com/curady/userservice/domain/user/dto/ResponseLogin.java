package com.curady.userservice.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLogin {
    private Long id;
    private String nickname;
    private String token;
    private String refreshToken;
}