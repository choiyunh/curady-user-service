package com.curady.userservice.domain.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class RequestSignup {
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    private String email;
    @NotEmpty(message = " 비밀번호는 필수 입력 값입니다.")
    private String password;
}
