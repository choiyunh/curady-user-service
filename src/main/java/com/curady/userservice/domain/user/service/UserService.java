package com.curady.userservice.domain.user.service;

import com.curady.userservice.domain.user.dto.RequestUserInfo;
import com.curady.userservice.domain.user.dto.ResponseSignup;
import com.curady.userservice.domain.user.dto.ResponseUserInfo;
import com.curady.userservice.domain.user.dto.ResponseUserNicknameAndImage;

import java.util.List;

public interface UserService {
    ResponseSignup createUserInfo(RequestUserInfo request, String id);
    ResponseUserInfo getUserInfo(String id);
    Boolean checkUserEmailAuth(Long id);
    List<ResponseUserNicknameAndImage> getUsersNicknameAndImage(List<Long> list);
    void deleteUser(String userId);
}
