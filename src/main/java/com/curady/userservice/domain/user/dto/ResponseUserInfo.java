package com.curady.userservice.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserInfo {
    private String email;
    private String nickname;
    private String imageUrl;
    private String gitUrl;
    private String blogUrl;
    private String description;
}