package com.springboot.apache.cxf.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalException extends RuntimeException {

    private final Integer responseCode;
    private final String url;
    private final String responseHeaders;
    private final String rootCause;

    public TechnicalException(String message, Integer responseCode, String url, String responseHeaders, String rootCause) {
        super(message);
        this.responseCode = responseCode;
        this.url = url;
        this.responseHeaders = responseHeaders;
        this.rootCause = rootCause;
    }
}
