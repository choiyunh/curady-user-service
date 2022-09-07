package com.curady.userservice.web.controller;

import com.curady.userservice.domain.mapper.UserMapper;
import com.curady.userservice.domain.entity.User;
import com.curady.userservice.domain.result.MultipleResult;
import com.curady.userservice.domain.result.SingleResult;
import com.curady.userservice.domain.service.ResponseService;
import com.curady.userservice.domain.service.UserService;
import com.curady.userservice.web.dto.RequestUserInfo;
import com.curady.userservice.web.dto.ResponseSignup;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;
    @Operation(description = "유저의 성향과 기타 정보를 등록합니다.")
    @PatchMapping("/userInfo")
    public SingleResult<ResponseSignup> createUserInfo(@RequestBody RequestUserInfo requestUserInfo) {
        ResponseSignup responseSignup = userService.createUserInfo(requestUserInfo);
        return responseService.getSingleResult(responseSignup);
    }
}
