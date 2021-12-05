package com.springboot.project.cipher.impl.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

@Service
public class CipherService {

    private static final String DEFAULT_CHARSET = "UTF-8";


    public String encodeBase64(String data) {
        return Base64Utils.encodeToString(data.getBytes());
    }

    public String decodeBase64(String data) {
        return new String(Base64Utils.decode(data.getBytes()));
    }

    public String encodeUrl(String urlString) {
        return UriUtils.encode(urlString, DEFAULT_CHARSET);
    }

    public String decodeUrl(String urlString) {
        return UriUtils.decode(urlString, DEFAULT_CHARSET);
    }

}
