package com.curady.userservice.web.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class RequestTendency {
    private String tendencyType;
    private String tendencyName;
}
