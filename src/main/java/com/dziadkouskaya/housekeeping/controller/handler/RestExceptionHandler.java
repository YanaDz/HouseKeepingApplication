package com.dziadkouskaya.housekeeping.controller.handler;

import com.dziadkouskaya.housekeeping.exception.ApplicationException;
import com.dziadkouskaya.housekeeping.exception.ResourceNotFoundException;
import com.dziadkouskaya.housekeeping.exception.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.dziadkouskaya.housekeeping.utils.Constants.ACCESS_DENIED_MESSAGE;
import static com.dziadkouskaya.housekeeping.utils.Constants.INNER_SERVER_ERROR_MESSAGE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<RestErrorResponse> handleBindException(ResourceNotFoundException ex, WebRequest request) {
        return handleInternal(ex, INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<RestErrorResponse> handleBindException(ApplicationException ex, WebRequest request) {
        return handleInternal(ex, INTERNAL_SERVER_ERROR, INNER_SERVER_ERROR_MESSAGE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return handleInternal(ex, FORBIDDEN, ACCESS_DENIED_MESSAGE);
    }


    private ResponseEntity<RestErrorResponse> handleInternal(Exception ex, HttpStatus status, String error) {
        log.error(error, ex);
        return new ResponseEntity<>(new RestErrorResponse(error), new HttpHeaders(), status);
    }
}
