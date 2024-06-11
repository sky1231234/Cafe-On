package com.example.order.cafe.domain;

import com.example.order.cafe.errorMsg.TemperatureOptionErrorMsg;
import com.example.order.global.common.BaseTimeEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class TemperatureOption extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cafeMenuId")
    private final CafeMenu cafeMenu;

    @Enumerated(EnumType.STRING)
    private final TemperatureType temperatureType;

    private TemperatureOption(CafeMenu cafeMenu, TemperatureType temperatureType){
        validation(cafeMenu, temperatureType);
        this.cafeMenu = cafeMenu;
        this.temperatureType = temperatureType;
    }

    public static TemperatureOption of(CafeMenu cafeMenu, TemperatureType temperatureType){
        return new TemperatureOption(cafeMenu, temperatureType);
    }

    public void validation(CafeMenu cafeMenu, TemperatureType temperatureType){
        validate_cafeMenu(cafeMenu);
        validate_temperatureType(temperatureType);
    }

    private void validate_cafeMenu(CafeMenu cafeMenu){
        if (cafeMenu == null) {
            throw new IllegalArgumentException(TemperatureOptionErrorMsg.CAFE_MENU_NON_NULL_ERROR_MSG.getValue());
        }
    }

    private void validate_temperatureType(TemperatureType temperatureType){
        if (temperatureType == null || temperatureType == TemperatureType.UNKNOWN) {
            throw new IllegalArgumentException(TemperatureOptionErrorMsg.TEMPERATURE_TYPE_INVALID_ERROR_MSG.getValue());
        }
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureOption temperatureOption = (TemperatureOption) o;

        return Objects.equals(cafeMenu, temperatureOption.cafeMenu)
                && temperatureType == temperatureOption.temperatureType;

    }

}
