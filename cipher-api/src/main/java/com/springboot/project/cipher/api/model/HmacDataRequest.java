package com.springboot.project.cipher.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HmacDataRequest {

    private String fullName;
    private String city;
    private String gender;
    private int amount;

}
