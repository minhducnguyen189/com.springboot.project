package com.springboot.project.hmac.app.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.hmac.api.HmacApi;
import com.springboot.project.hmac.api.model.HmacDataRequest;
import com.springboot.project.hmac.app.model.HmacMessage;
import com.springboot.project.hmac.app.service.HmacService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor =@__(@Autowired))
public class HmacApiImpl implements HmacApi {

    private final HmacService hmacService;
    private final ObjectMapper objectMapper;

    public String hmac(String nonce, String urlCalculate, String timestamp, HmacDataRequest inputData) {
        String hmacMessageString = this.buildHmacMessage(nonce, urlCalculate, timestamp, inputData);
        return hmacService.hmac(hmacMessageString);
    }

    public Boolean checkMatchHmac(String nonce, String urlCalculate, String timestamp, String hmac, HmacDataRequest inputData) {
        String hmacMessageString = this.buildHmacMessage(nonce, urlCalculate, timestamp, inputData);
        return hmacService.isHmacMatch(hmacMessageString, hmac);
    }


    private String buildHmacMessage(String nonce, String urlCalculate, String timestamp, HmacDataRequest inputData) {
        HmacMessage hmacMessage = new HmacMessage();
        hmacMessage.setNonce(nonce);
        hmacMessage.setTimestamp(urlCalculate);
        hmacMessage.setTimestamp(timestamp);
        hmacMessage.setHmacDataRequest(inputData);
        try {
            return objectMapper.writeValueAsString(hmacMessage);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("can not parse hmac message: ", ex);
        }
    }

}
