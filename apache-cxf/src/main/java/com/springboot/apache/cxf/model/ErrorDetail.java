package com.springboot.apache.cxf.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {

    private Integer status;
    private String calledUrl;
    private String responseHeaders;
    private String rootCause;
    private String errorMessage;
    private String timestamp;
    private String api;

}
