package com.example.order.cafe.dto.response;

import lombok.Getter;

@Getter
public class TemperatureOptionResponse {

    private final String option;

    public TemperatureOptionResponse(String option){
        this.option = option;
    }
}
