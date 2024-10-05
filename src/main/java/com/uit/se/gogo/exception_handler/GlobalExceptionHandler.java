package com.uit.se.gogo.exception_handler;

import com.uit.se.gogo.exception_handler.error.ApiError;
import com.uit.se.gogo.util.ExceptionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setErrorCode("ILLEGAL_ARGUMENT");
        return ExceptionUtil.buildResponseEntity(apiError);
    }
}
