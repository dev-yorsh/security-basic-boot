package com.application.dto.response;

import com.application.core.util.GenericUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    @JsonFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    private String fecha;
    private Integer statusCode;
    private String message;
    private T response;

    public BaseResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public BaseResponse(Integer statusCode, String message, T response) {
        this.statusCode = statusCode;
        this.message = message;
        this.response = response;
    }

    public String getfecha() {
        return GenericUtil.formatDate(LocalDateTime.now(), "dd/mm/yyyy hh:mm:ss");
    }
}
