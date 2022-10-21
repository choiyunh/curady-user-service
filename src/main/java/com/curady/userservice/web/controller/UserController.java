package com.curady.userservice.web.controller;

import com.curady.userservice.advice.exception.NicknameAlreadyExistsException;
import com.curady.userservice.domain.result.MultipleResult;
import com.curady.userservice.domain.result.SingleResult;
import com.curady.userservice.domain.service.ResponseService;
import com.curady.userservice.domain.service.UserService;
import com.curady.userservice.web.dto.RequestUserInfo;
import com.curady.userservice.web.dto.ResponseSignup;
import com.curady.userservice.web.dto.ResponseUserInfo;
import com.curady.userservice.web.dto.ResponseUserNicknameAndImage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    @Operation(description = "유저의 정보를 조회합니다.", summary = "유저 정보 조회")
    @GetMapping("/user/info")
    public SingleResult<ResponseUserInfo> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        return responseService.getSingleResult(userService.getUserInfo(id));
    }

    @Operation(description = "회원가입 시 유저의 성향과 기타 정보를 등록합니다.", summary = "유저 정보 등록")
    @PatchMapping("/user/info")
    public SingleResult<ResponseSignup> createUserInfo(@RequestBody RequestUserInfo requestUserInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();

        ResponseSignup responseSignup = userService.createUserInfo(requestUserInfo, id);
        return responseService.getSingleResult(responseSignup);
    }

    @Operation(description = "유저의 이메일 인증 여부를 확인합니다.", summary = "이메일 인증 여부 확인")
    @GetMapping("/user/{id}/emailAuth")
    public SingleResult<Boolean> checkEmailAuth(@PathVariable Long id) {
        Boolean response = userService.checkUserEmailAuth(id);
        return responseService.getSingleResult(response);
    }

    @Operation(description = "유저 닉네임, 이미지 조회(서비스 간 통신용)", summary = "유저 닉네임, 이미지 조회(서비스 간 통신용)")
    @GetMapping("/users/nickname/img")
    public MultipleResult<ResponseUserNicknameAndImage> getUsersNicknameAndImage(@RequestBody List<Long> list) {
        List<ResponseUserNicknameAndImage> response = userService.getUsersNicknameAndImage(list);
        return responseService.getMultipleResult(response);
    }
}
