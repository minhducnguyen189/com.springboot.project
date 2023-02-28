package com.springboot.apache.cxf.handler;

import com.springboot.apache.cxf.exception.TechnicalException;
import com.springboot.apache.cxf.model.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TechnicalException.class})
    public ResponseEntity<ErrorDetail> technicalExceptionHandler(TechnicalException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(OffsetDateTime.now().toString());
        errorDetail.setErrorMessage(ex.getMessage());
        errorDetail.setStatus(ex.getResponseCode());
        errorDetail.setRootCause(ex.getRootCause());
        errorDetail.setResponseHeaders(ex.getResponseHeaders());
        errorDetail.setCalledUrl(ex.getUrl());
        errorDetail.setApi(request.getDescription(false));
        if (errorDetail.getStatus() >= 400) {
            return new ResponseEntity<>(errorDetail, HttpStatus.valueOf(ex.getResponseCode()));
        }
        errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDetail> exceptionHandler(Exception ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(OffsetDateTime.now().toString());
        errorDetail.setErrorMessage(ex.getMessage());
        errorDetail.setApi(request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
