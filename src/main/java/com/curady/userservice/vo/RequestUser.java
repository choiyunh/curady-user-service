package com.curady.userservice.vo;

import com.curady.userservice.model.Tendency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class RequestUser {
    @NotNull(message = "Email cna not be null")
    private String email;

    @NotNull(message = " Password can not be null")
    private String password;
    @NotNull(message = "Nickname can not be null")
    private String nickname;

    private List<Tendency> tendency;
}
