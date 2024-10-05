package com.uit.se.gogo.util;

import com.uit.se.gogo.exception_handler.error.ApiError;
import org.springframework.http.ResponseEntity;

public class ExceptionUtil {
    public static ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
