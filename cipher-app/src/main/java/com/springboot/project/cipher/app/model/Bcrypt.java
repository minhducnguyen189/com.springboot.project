package com.springboot.project.cipher.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bcrypt {

    private String saltLength16;
    private int cost;

}
