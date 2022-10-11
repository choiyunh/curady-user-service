package com.curady.userservice.domain.auth.profile;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoProfile {
    KakaoAccount kakao_account;
    Properties properties;

    @Data
    public class KakaoAccount {
        private String email;
    }

    @Getter
    public class Properties {
        private String nickname;
    }
}
