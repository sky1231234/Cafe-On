package com.example.order.cafe.dto.request;


import com.example.order.cafe.domain.MenuCategory;
import com.example.order.cafe.domain.TemperatureOption;
import com.example.order.cafe.domain.TemperatureType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(force = true)
@Getter
public class CafeMenuCreateRequest {
    @NotNull
    private final long cafeId;
    @NotNull
    private final String menuName;
    @NotNull
    private final List<TemperatureType> temperatureTypeList;
    @NotNull
    private final MenuCategory menuCategory;
    @NotNull
    private final String explain;
    @NotNull
    private final int stock;
    @NotNull
    private final int price;

    public CafeMenuCreateRequest(long cafeId, String menuName, List<TemperatureType> temperatureTypeList, MenuCategory menuCategory, String explain, int stock, int price){
        this.cafeId = cafeId;
        this.menuName = menuName;
        this.temperatureTypeList = temperatureTypeList;
        this.menuCategory = menuCategory;
        this.explain = explain;
        this.stock = stock;
        this.price = price;
    }

}
