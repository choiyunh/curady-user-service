package com.curady.userservice.domain.service;

import com.curady.userservice.domain.entity.User;
import com.curady.userservice.web.dto.RequestUserInfo;
import com.curady.userservice.web.dto.ResponseSignup;

public interface UserService {
    ResponseSignup createUserInfo(RequestUserInfo request);
}
