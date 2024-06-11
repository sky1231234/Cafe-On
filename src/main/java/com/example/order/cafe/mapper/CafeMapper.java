package com.example.order.cafe.mapper;

import com.example.order.cafe.domain.*;
import com.example.order.cafe.dto.request.*;
import com.example.order.cafe.dto.response.BusinessHoursResponse;
import com.example.order.cafe.dto.response.CafeInfoResponse;
import com.example.order.cafe.dto.response.CafeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CafeMapper {

    private final CafeInfoMapper cafeInfoMapper;
    private final BusinessHoursMapper businessHoursMapper;

    public CafeResponse toCafeResponse(Cafe cafe){

        CafeInfoResponse toCafeInfoResponse =  cafeInfoMapper.toCafeInfoResponse(cafe.getCafeInfo());
        BusinessHoursResponse toBusinessHoursResponse = businessHoursMapper.toBusinessHoursResponse(cafe.getBusinessHours());

        return new CafeResponse(toCafeInfoResponse, toBusinessHoursResponse);
    };


    public Cafe toCafe(CafeInfoCreateRequest cafeInfoCreateRequest, BusinessHoursRequest businessHoursRequest) {
        if ( cafeInfoCreateRequest == null && businessHoursRequest == null) {
            return null;
        }

        CafeInfo cafeInfo = cafeInfoMapper.toCafeInfo(cafeInfoCreateRequest);
        BusinessHours businessHours = businessHoursMapper.toBusinessHours(businessHoursRequest);

        return Cafe.of(cafeInfo, businessHours);
    }

    public Cafe toCafe(CafeInfoUpdateRequest cafeInfoUpdateRequest, BusinessHoursUpdateRequest businessHoursUpdateRequest) {
        if ( cafeInfoUpdateRequest == null && businessHoursUpdateRequest == null) {
            return null;
        }

        CafeInfo cafeInfo = cafeInfoMapper.toCafeInfo(cafeInfoUpdateRequest);
        BusinessHours businessHours = businessHoursMapper.toBusinessHours(businessHoursUpdateRequest);

        return Cafe.of(cafeInfo, businessHours);
    }

}





