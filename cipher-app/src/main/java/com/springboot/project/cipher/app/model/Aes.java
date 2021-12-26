package com.springboot.project.cipher.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aes {

    private String secret;
    private String ivSecret;

}
