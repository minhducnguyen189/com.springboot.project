package com.springboot.project.json.schema.validator.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private String orderName;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private List<Item> items;

}
