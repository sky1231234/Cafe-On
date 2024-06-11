package com.example.order.cafe.domain;

public enum MenuCategory {
    COFFEE("커피"),
    LATTE("라떼"),
    ADE("에이드"),
    TEA("티")
    ;

    private final String value;

    MenuCategory(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}