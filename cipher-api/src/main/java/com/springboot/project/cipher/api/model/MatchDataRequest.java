package com.springboot.project.cipher.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDataRequest {

    private String rawData;
    private String hashedData;

}
