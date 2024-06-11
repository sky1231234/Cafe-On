package com.example.order.cafe.domain;

import com.example.order.cafe.errorMsg.CafeMenuErrorMsg;
import com.example.order.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class CafeMenu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private final long cafeId;

    @Column(nullable = false)
    private final String menuName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final MenuCategory menuCategory;
//
//    @OneToMany(mappedBy = "cafeMenu", cascade = CascadeType.ALL, orphanRemoval = true)
//    private final List<TemperatureOption> temperatureOptions;

    @Column(nullable = false)
    private final String explain;

    @Column(nullable = false)
    private final int stock;

    @Column(nullable = false)
    private final int price;

    @Transient
    private static final int MIN_NAME_LENGTH = 1;
    @Transient
    private static final int MIN_STOCK_LENGTH = 0;
    @Transient
    private static final int MIN_PRICE_LENGTH = 0;

    private CafeMenu(long cafeId, String menuName, MenuCategory menuCategory,
//                     List<TemperatureOption> temperatureOptions,
                     String explain, int stock, int price){
        validation(menuName,
//                temperatureOptions,
                stock, price);
        this.cafeId = cafeId;
        this.menuName = menuName;
        this.menuCategory = menuCategory;
//        this.temperatureOptions = new ArrayList<>(temperatureOptions);
        this.explain = explain;
        this.stock = stock;
        this.price = price;
    }

    public static CafeMenu of(long cafeId, String menuName, MenuCategory menuCategory,
//                              List<TemperatureOption> temperatureOptions,
                              String explain, int stock, int price){
        return new CafeMenu(cafeId, menuName, menuCategory,
//                temperatureOptions,
                explain, stock, price);
    }

    private void validation(String menuName,
//                            List<TemperatureOption> temperatureType,
                            int stock, int price) {
        validate_menuName(menuName);
//        validate_temperatureOption(temperatureType);
        validate_stock(stock);
        validate_price(price);

    }

    private void validate_menuName(String menuName){
        if(menuName.length() < MIN_NAME_LENGTH){
            throw new IllegalArgumentException(CafeMenuErrorMsg.MENU_NAME_LENGTH_ERROR_MESSAGE.getValue());
        }
    }


    private void validate_temperatureOption(List<TemperatureOption> temperatureType) {
        isTemperatureOptionNull(temperatureType);
        temperatureOption_empty(temperatureType);
    }

    private void isTemperatureOptionNull(List<TemperatureOption> temperatureType){
        if(temperatureType == null)
            throw new IllegalArgumentException(CafeMenuErrorMsg.TEMPERATURE_OPTION_NULL_ERROR_MESSAGE.getValue());
    }

    private void temperatureOption_empty(List<TemperatureOption> temperatureType){
        if(temperatureType.isEmpty()){
            throw new IllegalArgumentException(CafeMenuErrorMsg.TEMPERATURE_OPTION_EMPTY_ERROR_MESSAGE.getValue());
        }
    }


    private void validate_stock(int stock){
        if(stock < MIN_STOCK_LENGTH){
            throw new IllegalArgumentException(CafeMenuErrorMsg.STOCK_UNDER_ZERO_ERROR_MESSAGE.getValue());
        }
    }

    private void validate_price(int price){
        if(price < MIN_PRICE_LENGTH){
            throw new IllegalArgumentException(CafeMenuErrorMsg.PRICE_UNDER_ZERO_ERROR_MESSAGE.getValue());
        }
    }

    public boolean isMenuName(String menuName){
        return this.menuName.equals(menuName);
    }

//    public List<TemperatureOption> getTemperatureType(){
//        return List.copyOf(temperatureOptions);
//    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CafeMenu cafeMenu = (CafeMenu) o;
        return Objects.equals(menuName, cafeMenu.menuName) &&
//                Objects.equals(temperatureOptions, cafeMenu.temperatureOptions) &&
                Objects.equals(price, cafeMenu.price)
                ;
    }


}
