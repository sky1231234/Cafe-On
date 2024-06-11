package com.example.order.cafe.mapper;

import com.example.order.cafe.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TemperatureOptionMapper {

    public List<TemperatureOption> toTemperatureOption(CafeMenu cafeMenu, List<TemperatureType> temperatureTypeList){

        List<TemperatureOption> temperatureOptions = temperatureTypeList.stream()
                .map(type -> TemperatureOption.of(cafeMenu,type))
                .collect(Collectors.toList());

        return temperatureOptions;

    };

}





