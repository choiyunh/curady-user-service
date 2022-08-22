package com.curady.userservice.dto;

import com.curady.userservice.model.UserTendency;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;

    private String password;
    private String nickname;
    private String imageUrl;
    private String uuid;
    private Date createdAt;
    private Date updatedAt;
    private String encryptedPwd;
    private List<UserTendency> userTendency;
}
