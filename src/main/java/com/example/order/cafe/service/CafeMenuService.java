package com.example.order.cafe.service;

import com.example.order.cafe.domain.*;
import com.example.order.cafe.dto.request.CafeMenuCreateRequest;
import com.example.order.cafe.dto.response.CafeMenuDetailResponse;
import com.example.order.cafe.dto.response.CafeMenuResponse;
import com.example.order.cafe.mapper.CafeMenuMapper;
import com.example.order.cafe.mapper.TemperatureOptionMapper;
import com.example.order.cafe.repository.CafeMenuRepository;
import com.example.order.cafe.repository.TemperatureOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CafeMenuService {
    private final CafeMenuRepository cafeMenuRepository;
    private final TemperatureOptionRepository temperatureOptionRepository;
    private final CafeMenuMapper cafeMenuMapper;
    private final TemperatureOptionMapper temperatureOptionMapper;

    @Transactional
    public CafeMenu registerCafeMenu(long cafeId, String menuName, List<TemperatureType> temperatureTypeList, MenuCategory menuCategory, String explain, int stock, int price) {

        CafeMenu cafeMenu = cafeMenuMapper.toCafeMenu(cafeId, menuName, menuCategory, explain, stock, price);
        List<TemperatureOption> temperatureOption = temperatureOptionMapper.toTemperatureOption(cafeMenu, temperatureTypeList);

        CafeMenu saveCafeMenu = cafeMenuRepository.save(cafeMenu);
        temperatureOptionRepository.saveAll(temperatureOption);

        return saveCafeMenu;
    }


    public CafeMenu check_existCafeMenu(long menuId){
        return cafeMenuRepository.findById(menuId)
                .orElseThrow(NoSuchElementException::new);
    }



}
