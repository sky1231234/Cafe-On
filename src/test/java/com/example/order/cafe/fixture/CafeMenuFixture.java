package com.example.order.cafe.fixture;

import com.example.order.cafe.domain.CafeMenu;
import com.example.order.cafe.domain.MenuCategory;

public class CafeMenuFixture {

    public static final long 카페ID = 1L;
    public static final String 메뉴명 = "아메리카노";
    public static final MenuCategory 메뉴카테고리 = MenuCategory.COFFEE;
    public static final String 설명 = "에스프레소를 부드럽게 즐길 수 있습니다.";
    public static final int 재고 = 1000;
    public static final int 가격 = 4500;

    public static CafeMenu 카페메뉴_기본생성(){
        return CafeMenu.of(카페ID, 메뉴명, 메뉴카테고리, 설명, 재고, 가격);
    }
}
