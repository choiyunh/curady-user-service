package com.curady.userservice.dto;

import lombok.Data;

import java.util.Date;

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
}
