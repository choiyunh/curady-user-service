package com.curady.userservice.domain.service;

import com.curady.userservice.web.dto.RequestUserInfo;
import com.curady.userservice.web.dto.ResponseSignup;
import com.curady.userservice.web.dto.ResponseUserInfo;

public interface UserService {
    ResponseSignup createUserInfo(RequestUserInfo request, String email);
    ResponseUserInfo getUserInfoByEmail(String email);
    Boolean checkUserEmailAuth(Long id);
}
