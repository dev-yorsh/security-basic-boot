package com.application.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.application.core.constants.Constants.RegularExpressions.*;

@Data
public class RoleRequest {

    private Integer idRole;
    private String name;
    private String detail;

}
