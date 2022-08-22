package com.curady.userservice.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestUser {
    @NotNull(message = "Email cna not be null")
    private String email;

    @NotNull(message = " Password can not be null")
    private String password;
    @NotNull(message = "Nickname can not be null")
    private String nickname;

    private String jobTendency;
    private String languageTendency;
    private String lectureTendency;

}
