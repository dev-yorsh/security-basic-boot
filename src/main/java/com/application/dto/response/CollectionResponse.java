package com.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CollectionResponse<T> {

    private List<T> data;

    @JsonProperty("actual_page")
    private Integer actualPage;

    @JsonProperty("number_elements")
    private Integer numberElements;

    @JsonProperty("total_pages")
    private Integer totalPage;

    @JsonProperty("total_elements")
    private Long totalElements;

}
