package com.example.order.cafe.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CafeMenuDetailResponse {
    private final String menuName;
    private final int price;
    private final String explain;
    private final List<TemperatureOptionResponse> temperatureOptionList;

    public CafeMenuDetailResponse(String menuName, int price, String explain, List<TemperatureOptionResponse> temperatureOptionList){
        this.menuName = menuName;
        this.price = price;
        this.explain = explain;
        this.temperatureOptionList = temperatureOptionList;
    }

}
