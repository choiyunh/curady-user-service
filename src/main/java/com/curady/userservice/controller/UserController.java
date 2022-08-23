package com.curady.userservice.controller;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.mapper.UserMapper;
import com.curady.userservice.service.UserService;
import com.curady.userservice.service.UserTendencyService;
import com.curady.userservice.vo.RequestUser;
import com.curady.userservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user-service")
public class UserController {
    Environment env;
    UserService userService;
    UserTendencyService userTendencyService;

    @Autowired
    public UserController(Environment env, UserService userService, UserTendencyService userTendencyService) {
        this.env = env;
        this.userService = userService;
        this.userTendencyService = userTendencyService;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's working in user service on PORT %s",
                env.getProperty("local.server.port"));
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        UserDto userDto = UserMapper.INSTANCE.requestToDto(user);
        userService.createUser(userDto);
        userTendencyService.createUserTendency(userDto);

        ResponseUser responseUser = UserMapper.INSTANCE.dtoToResponse(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
