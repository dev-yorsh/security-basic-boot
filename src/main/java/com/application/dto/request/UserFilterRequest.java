package com.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserFilterRequest extends BaseRequest {

    @JsonProperty("user_id")
    private Integer idUser;
    private String username;
    private String name;
    private String email;

    @JsonProperty("account_status_code")
    private String accountStatusCode;
    private Boolean enabled;
    private List<String> role = new ArrayList<>();

}
