package com.springboot.project.cipher.impl.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bcrypt {

    private String saltLength16;
    private int cost;

}
