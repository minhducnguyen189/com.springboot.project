package com.springboot.project.root.application.controller;

import com.springboot.project.hmac.api.HmacApi;
import com.springboot.project.hmac.api.model.HmacDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HmacController {

    @Autowired(required = false)
    private HmacApi hmacApi;

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hmac", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> hmac(@RequestHeader(name = "x-nonce") String nonce,
                                       @RequestHeader(name = "x-uri-calculate") String urlCalculate,
                                       @RequestHeader(name = "x-timestamp") String timestamp,
                                       @RequestBody HmacDataRequest inputData) {
        return new ResponseEntity<>(hmacApi.hmac(nonce, urlCalculate, timestamp, inputData), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hmac/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkMatchHmac(@RequestHeader(name = "x-nonce") String nonce,
                                                  @RequestHeader(name = "x-uri-calculate") String urlCalculate,
                                                  @RequestHeader(name = "x-timestamp") String timestamp,
                                                  @RequestHeader(name = "x-hmac") String hmac,
                                                  @RequestBody HmacDataRequest inputData) {
        return new ResponseEntity<>(hmacApi.checkMatchHmac(nonce, urlCalculate, timestamp, hmac, inputData), HttpStatus.OK);
    }

}
