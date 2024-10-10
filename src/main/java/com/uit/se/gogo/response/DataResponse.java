package com.uit.se.gogo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataResponse <T> {
    private T data;

    public DataResponse(T data) {
        this.data = data;
    }
}
