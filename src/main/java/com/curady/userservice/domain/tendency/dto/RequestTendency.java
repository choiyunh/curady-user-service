package com.curady.userservice.domain.tendency.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class RequestTendency {
    private String tendencyType;
    private String tendencyName;
}
