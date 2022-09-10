package com.curady.userservice.web.dto;

import com.curady.userservice.domain.entity.Tendency;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RequestUserInfo {
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    private String email;
    private List<RequestTendency> requestTendencies;
    private String nickname;
    private String gitUrl;
    private String blogUrl;
    private String description;
}
