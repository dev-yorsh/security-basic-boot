package com.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {

    private Integer page;
    private Integer limit;
}
