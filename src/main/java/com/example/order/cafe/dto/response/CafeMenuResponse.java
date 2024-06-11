package com.example.order.cafe.dto.response;

import lombok.Getter;

@Getter
public class CafeMenuResponse {
    private final String menuName;
    private final int price;

    public CafeMenuResponse(String menuName, int price){
        this.menuName = menuName;
        this.price = price;
    }

}
