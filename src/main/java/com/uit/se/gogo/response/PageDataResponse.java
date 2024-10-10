package com.uit.se.gogo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PageDataResponse <T> extends DataResponse<T> {
    private Integer page;
    private Integer size;
    private Long total;
    @JsonProperty("total_page")
    private Long totalPage;

    public PageDataResponse(T data) {
        super(data);
    }
}
