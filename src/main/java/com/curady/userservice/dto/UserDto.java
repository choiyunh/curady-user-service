package com.curady.userservice.dto;

import com.curady.userservice.entity.Tendency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String nickname;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
    private String encryptedPwd;
    private List<Tendency> tendency;
}
