package com.curady.userservice.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUserNicknameAndImage {
    private String nickname;
    private String imageUrl;
}
