package com.curady.userservice.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> tendencyList = new ArrayList<>();
}