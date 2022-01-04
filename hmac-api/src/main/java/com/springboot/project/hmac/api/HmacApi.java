package com.springboot.project.hmac.api;

import com.springboot.project.hmac.api.model.HmacDataRequest;

public interface HmacApi {

    String hmac(String nonce, String urlCalculate,
                String timestamp, HmacDataRequest inputData);

    Boolean checkMatchHmac(String nonce, String urlCalculate, String timestamp,
                           String hmac, HmacDataRequest inputData);

}
