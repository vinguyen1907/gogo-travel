package com.uit.se.gogo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDataResponse <T> {
    private List<T> data;
    private Integer page;
    private Integer size;
    private Long total;
    @JsonProperty("total_page")
    private int totalPage;

    public PageDataResponse(Page<T> page) {
        this.data = page.get().toList();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.total = page.getTotalElements();
        this.totalPage = page.getTotalPages();
    }
}
