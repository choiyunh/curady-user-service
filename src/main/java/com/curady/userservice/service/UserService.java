package com.curady.userservice.service;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.model.User;

import java.util.Optional;

public interface UserService {
    void createUser(UserDto userDto);

    User getUserByUuid(String uuid);
    Iterable<User> getUserByAll();
}
