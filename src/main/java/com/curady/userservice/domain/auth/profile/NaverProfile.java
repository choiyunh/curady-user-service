package com.curady.userservice.domain.auth.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaverProfile {
    Response response;

    @Data
    public class Response {
        private String email;
        private String nickname;
    }
}

