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

    @Operation(description = "유저의 이메일로 전송할 인증 링크에 사용될 이메일 인증 API입니다." +
            "유저의 이메일 인증 여부를 확인하는 API는 /emailAuth 입니다.")
    @GetMapping("/confirmEmail")
    public SingleResult<String> confirmEmail(@ModelAttribute RequestEmailAuth requestEmailAuth) {
        signService.confirmEmail(requestEmailAuth);
        return responseService.getSingleResult("이메일 인증이 완료되었습니다.");
    }

    @Operation(description = "email과 password를 입력하여 로그인을 진행합니다.")
    @PostMapping("/login")
    public SingleResult<ResponseLogin> login(@RequestBody RequestLogin requestLogin) {
        ResponseLogin responseLogin = signService.loginUser(requestLogin);
        return responseService.getSingleResult(responseLogin);
    }

    @Operation(description = "kakao 로그인을 진행합니다.")
    @PostMapping("/login/kakao")
    public SingleResult<ResponseLogin> loginByKakao(@RequestBody AuthCode authCode) {
        ResponseLogin responseLogin = signService.loginMemberByProvider(authCode.getCode(), "kakao");
        return responseService.getSingleResult(responseLogin);
    }
}
