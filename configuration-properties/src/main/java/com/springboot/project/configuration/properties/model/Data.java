package com.springboot.project.configuration.properties.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 prefix = "data" it is mean this object is "data" in application.yaml
 And name, amount and price are 3 attributes in this object.
 @Component will make this object become an spring bean and we can use it everywhere with annotation @Autowired
 */

@Component
@ConfigurationProperties(prefix = "data")
public class Data {

    private String name;
    private Integer amount;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


