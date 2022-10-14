package com.springboot.project.com.exception.handler.app.handler;

import com.springboot.project.com.exception.handler.app.exception.ResourceNotFoundException;
import com.springboot.project.com.exception.handler.app.model.exception.ErrorDetail;
import com.springboot.project.com.exception.handler.app.model.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessageKey(), ex.getParam(), locale);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(errorMessage);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setApi(request.getDescription(false));
        errorResponse.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setKey(ex.getMessageKey());
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setStacktrace(Arrays.toString(ex.getStackTrace()));
        errorResponse.setDetail(errorDetail);
        return errorResponse;
    }


}
