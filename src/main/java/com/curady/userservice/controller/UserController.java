package com.curady.userservice.controller;

import com.curady.userservice.mapper.UserMapper;
import com.curady.userservice.entity.User;
import com.curady.userservice.service.UserService;
import com.curady.userservice.vo.ResponseSignup;
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

    @GetMapping("/users")
    public ResponseEntity<List<ResponseSignup>> getUsers() {
        Iterable<User> users = userService.getAllUsers();

        List<ResponseSignup> result = new ArrayList<>();
        users.forEach(v -> {
            result.add(UserMapper.INSTANCE.entityToResponse(v));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
