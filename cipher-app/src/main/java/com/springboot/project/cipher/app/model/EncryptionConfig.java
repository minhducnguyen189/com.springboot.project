package com.springboot.project.cipher.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncryptionConfig {

    private Aes aes;
    private Sha256 sha256;
    private Sha512 sha512;
    private Bcrypt bcrypt;

}
