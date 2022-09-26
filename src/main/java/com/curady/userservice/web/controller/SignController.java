package com.curady.userservice.web.controller;

import com.curady.userservice.domain.auth.AuthCode;
import com.curady.userservice.domain.result.SingleResult;
import com.curady.userservice.domain.service.ResponseService;
import com.curady.userservice.domain.service.SignService;
import com.curady.userservice.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;
    private final ResponseService responseService;

    @Operation(description = "email과 password를 입력하여 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public SingleResult<ResponseSignup> createUser(@RequestBody RequestSignup requestSignup) throws MessagingException {
        ResponseSignup responseSignup = signService.createUser(requestSignup);
        return responseService.getSingleResult(responseSignup);
    }

    @Operation(description = "email과 password를 입력하여 로그인을 진행합니다.")
    @PostMapping("/login")
    public SingleResult<ResponseLogin> login(@RequestBody RequestLogin requestLogin) {
        ResponseLogin responseLogin = signService.loginUser(requestLogin);
        return responseService.getSingleResult(responseLogin);
    }

    @Operation(description = "소셜 로그인을 진행합니다.")
    @PostMapping("/login/{provider}")
    public SingleResult<ResponseLogin> loginByProvider(@RequestBody AuthCode authCode, @PathVariable String provider) {
        ResponseLogin responseLogin = signService.loginUserByProvider(authCode.getCode(), provider);
        return responseService.getSingleResult(responseLogin);
    }
}
