package com.curady.userservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RequestSignup {
    @NotNull(message = "이메일은 필수 입력 값입니다.")
    private String username;

    @NotNull(message = " 비밀번호는 필수 입력 값입니다.")
    private String password;
}
