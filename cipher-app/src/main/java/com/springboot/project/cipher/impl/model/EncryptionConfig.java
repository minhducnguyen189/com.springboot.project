package com.springboot.project.cipher.impl.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncryptionConfig {

    private Aes aes;
    private Sha256 sha256;
    private Bcrypt bcrypt;
    private Hmac hmac;

}
