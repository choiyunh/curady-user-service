package com.curady.userservice.domain.user.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestLogin {
    @NotNull(message = "이메일은 필수 입력 값입니다.")
    private String email;
    @NotNull(message = " 비밀번호는 필수 입력 값입니다.")
    private String password;
}