package com.curady.userservice.service;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.entity.User;
import com.curady.userservice.vo.ResponseSignup;

import javax.mail.MessagingException;

public interface UserService {
    Iterable<User> getAllUsers();
}
