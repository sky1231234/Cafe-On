package com.example.order.cafe.domain;

public enum TemperatureType {
    ICE("아이스"),
    HOT("핫"),
    UNKNOWN("예외")
    ;

    private final String value;

    TemperatureType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}