package com.curady.userservice.web.controller;

import com.curady.userservice.advice.exception.NicknameAlreadyExistsException;
import com.curady.userservice.domain.result.SingleResult;
import com.curady.userservice.domain.service.ResponseService;
import com.curady.userservice.domain.service.UserService;
import com.curady.userservice.web.dto.RequestUserInfo;
import com.curady.userservice.web.dto.ResponseSignup;
import com.curady.userservice.web.dto.ResponseUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    @Operation(description = "유저의 정보를 조회합니다.")
    @GetMapping("/user/info")
    public SingleResult<ResponseUserInfo> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return responseService.getSingleResult(userService.getUserInfoByEmail(email));
    }

    @Operation(description = "회원가입 시 유저의 성향과 기타 정보를 등록합니다.")
    @PatchMapping("/user/info")
    public SingleResult<ResponseSignup> createUserInfo(@RequestBody RequestUserInfo requestUserInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        try {
            ResponseSignup responseSignup = userService.createUserInfo(requestUserInfo, email);
            return responseService.getSingleResult(responseSignup);
        } catch (DataIntegrityViolationException e) {
            throw new NicknameAlreadyExistsException();
        }
    }

    @Operation(description = "유저의 이메일 인증 여부를 확인합니다.")
    @GetMapping("/user/{id}/emailAuth")
    public SingleResult<Boolean> checkEmailAuth(@PathVariable Long id) {
        Boolean response = userService.checkUserEmailAuth(id);
        return responseService.getSingleResult(response);
    }
}
