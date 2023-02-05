package com.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.application.core.constants.Constants.MessageValidation.*;
import static com.application.core.constants.Constants.RegularExpressions.REGEXP_ONLY_LETTERS;

@Getter
@Setter
public class UserRequest {

    private Integer idUser;

    @Size(min = 3, max = 250, message = NAME_SIZE_ERROR)
    @Pattern(regexp = REGEXP_ONLY_LETTERS, message = NAME_CHARACTER_ERROR)
    private String name;

    @Email(message = EMAIL_ERROR)
    private String email;

    @Size(min = 3, max = 100, message = USERNAME_ERROR)
    private String username;

    private String photo;

    @Valid
    private Set<RoleRequest> roleList;

}
