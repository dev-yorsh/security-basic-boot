package com.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

import static com.application.core.constants.Constants.MessageValidation.PASSWORD_ERROR;
import static com.application.core.constants.Constants.RegularExpressions.REGEXP_ONLY_PASSWORD;

@Getter
@Setter
public class ChangePasswordRequest {

    private String oldPassword;
    @Pattern(regexp = REGEXP_ONLY_PASSWORD, message = PASSWORD_ERROR)
    private String newPassword;
    private String passwordRepeat;
}
