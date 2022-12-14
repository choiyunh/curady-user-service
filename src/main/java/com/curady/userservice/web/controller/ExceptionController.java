package com.curady.userservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.curady.userservice.global.advice.exception.AuthenticationEntryPointException;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping(value = "/entry")
    public void EntryPointException() {
        throw new AuthenticationEntryPointException();
    }

    @GetMapping(value = "/denied")
    public void AccessDeniedException() {
        throw new AccessDeniedException("");
    }
}
