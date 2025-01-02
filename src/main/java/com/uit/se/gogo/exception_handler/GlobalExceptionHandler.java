package com.uit.se.gogo.exception_handler;

import org.apache.coyote.BadRequestException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uit.se.gogo.exception.CommonException;
import com.uit.se.gogo.exception.RoomNotAvailableException;
import com.uit.se.gogo.exception_handler.error.ApiError;
import com.uit.se.gogo.util.ExceptionUtil;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
//        ApiError apiError = new ApiError(BAD_REQUEST);
//        apiError.setMessage(ex.getMessage());
//        apiError.setErrorCode("ILLEGAL_ARGUMENT");
//        return ExceptionUtil.buildResponseEntity(apiError);
//    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(RoomNotAvailableException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setErrorCode("ROOM_NOT_AVAILABLE");
        return ExceptionUtil.buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setErrorCode("BAD_REQUEST");
        return ExceptionUtil.buildResponseEntity(apiError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> userNameNotFound(UsernameNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setErrorCode("USERNAME_NOT_FOUND");
        return ExceptionUtil.buildResponseEntity(apiError);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Object> commonException(CommonException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setErrorCode("BAD_REQUEST");
        return ExceptionUtil.buildResponseEntity(apiError);
    }
}
