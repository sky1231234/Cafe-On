package com.example.order.cafe.mapper;

import com.example.order.cafe.domain.*;
import org.springframework.stereotype.Component;

@Component
public class CafeMenuMapper {

    public CafeMenu toCafeMenu(long cafeId, String menuName, MenuCategory menuCategory, String explain, int stock, int price){
        return CafeMenu.of(cafeId, menuName, menuCategory, explain, stock, price);
    };

}





