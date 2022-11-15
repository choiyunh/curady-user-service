package com.curady.userservice.domain.user.dto;

import com.curady.userservice.domain.tendency.dto.RequestTendency;
import lombok.Data;

import java.util.List;

@Data
public class RequestUserInfo {
    private List<RequestTendency> requestTendencies;
    private String nickname;
    private String gitUrl;
    private String blogUrl;
    private String description;
}
