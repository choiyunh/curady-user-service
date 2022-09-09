package com.curady.userservice.web.controller;

import com.curady.userservice.domain.service.SignService;
import com.curady.userservice.web.dto.RequestEmailAuth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ViewController {
    private final SignService signService;

    @Operation(description = "유저의 이메일로 전송할 인증 링크에 사용될 이메일 인증 API입니다." +
            "유저의 이메일 인증 여부를 확인하는 API는 /emailAuth 입니다.")
    @GetMapping("/confirmEmail")
    public String confirmEmail(@ModelAttribute RequestEmailAuth requestEmailAuth) {
        signService.confirmEmail(requestEmailAuth);
        return "response/successEmailAuth";
    }
}
