package com.curady.userservice.domain.service;

import com.curady.userservice.web.dto.RequestUserInfo;
import com.curady.userservice.web.dto.ResponseSignup;
import com.curady.userservice.web.dto.ResponseUserInfo;
import com.curady.userservice.web.dto.ResponseUserNicknameAndImage;

import java.util.List;

public interface UserService {
    ResponseSignup createUserInfo(RequestUserInfo request, String id);
    ResponseUserInfo getUserInfo(String id);
    Boolean checkUserEmailAuth(Long id);
    List<ResponseUserNicknameAndImage> getUsersNicknameAndImage(List<Long> list);
}
