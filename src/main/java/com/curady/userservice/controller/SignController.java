package com.curady.userservice.controller;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.mapper.UserMapper;
import com.curady.userservice.service.SignService;
import com.curady.userservice.vo.RequestEmailAuth;
import com.curady.userservice.vo.RequestSignup;
import com.curady.userservice.vo.ResponseSignup;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;

    @Operation(description = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<ResponseSignup> createUser(@RequestBody RequestSignup requestSignup) throws MessagingException {
        UserDto userDto = UserMapper.INSTANCE.requestToDto(requestSignup);
        ResponseSignup responseSignup = signService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseSignup);
    }

    @Operation(description = "이메일 인증")
    @GetMapping("/confirmEmail")
    public void confirmEmail(@RequestParam("username") String username,
                           @RequestParam("authToken") String authToken) {
        log.info(username);
        signService.confirmEmail(RequestEmailAuth.builder()
                .email(username)
                .authToken(authToken)
                .build());
    }
}
