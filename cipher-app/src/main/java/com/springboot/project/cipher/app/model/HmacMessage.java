package com.springboot.project.cipher.app.model;

import com.springboot.project.cipher.api.model.HmacDataRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HmacMessage {

    private String nonce;
    private String urlCalculate;
    private String timestamp;
    private HmacDataRequest hmacDataRequest;

}
