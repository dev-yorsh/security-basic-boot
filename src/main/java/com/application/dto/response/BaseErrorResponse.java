package com.application.dto.response;

import com.application.core.util.GenericUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class BaseErrorResponse {

    @JsonFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    private String date;

    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("user_message")
    private String userMessage;

    @JsonProperty("technical_message")
    private String technicalMessage;

    @JsonProperty("error_details")
    private List<String> details;

    public BaseErrorResponse(Integer statusCode, String userMessage, String technicalMessage,
                             List<String> details) {
        this.statusCode = statusCode;
        this.userMessage = userMessage;
        this.technicalMessage = technicalMessage;
        this.details = details;
    }

    public BaseErrorResponse(Integer statusCode, String userMessage, String technicalMessage) {
        this.date = date;
        this.statusCode = statusCode;
        this.userMessage = userMessage;
        this.technicalMessage = technicalMessage;
    }

    public String getDate() {
        return GenericUtil.formatDate(LocalDateTime.now(), "dd/mm/yyyy hh:mm:ss");
    }

}
