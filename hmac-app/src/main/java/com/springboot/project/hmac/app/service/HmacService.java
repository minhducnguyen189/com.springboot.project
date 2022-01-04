package com.springboot.project.hmac.app.service;

import com.springboot.project.hmac.app.model.HmacConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

@Service
public class HmacService {

    private static final String HMAC_SHA256 = "HmacSHA256";

    @Autowired
    private HmacConfig hmacConfig;

    public String hmac(String data) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    hmacConfig.getSecret().getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hmacBytes);
        } catch (Exception ex) {
            throw new RuntimeException("Can not Hmac Data", ex);
        }
    }

    public boolean isHmacMatch(String data, String hmacData) {
        String reHmacData = this.hmac(data);
        return reHmacData.equals(hmacData);
    }

}
