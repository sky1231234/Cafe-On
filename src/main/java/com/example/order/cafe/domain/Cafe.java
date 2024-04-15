package com.example.order.cafe.domain;

import com.example.order.cafe.errorMsg.CafeErrorMsg;
import lombok.Getter;

import java.util.Objects;

public class Cafe {

    private final CafeInfo cafeInfo;

    private final BusinessHours businessHours;

    private Cafe(CafeInfo cafeInfo, BusinessHours businessHours){
        validation(cafeInfo, businessHours);
        this.cafeInfo = cafeInfo;
        this.businessHours = businessHours;
    }

    public static Cafe of(CafeInfo cafeInfo, BusinessHours businessHours){
        return new Cafe(cafeInfo, businessHours);
    }

    public void validation(CafeInfo cafeInfo, BusinessHours businessHours){

        check_cafeInfo_null(cafeInfo);
        check_businessHours_null(businessHours);
    }

    public void check_cafeInfo_null(CafeInfo cafeInfo){
        if(cafeInfo == null){
            throw new IllegalArgumentException(CafeErrorMsg.CAFE_INFO_NULL_ERROR_MESSAGE.getValue());
        }
    }

    public void check_businessHours_null(BusinessHours businessHours){
        if(businessHours == null){
            throw new IllegalArgumentException(CafeErrorMsg.BUSINESS_HOURS_NULL_ERROR_MESSAGE.getValue());
        }
    }
    public CafeInfo getCafeInfo(){
        return CafeInfo.of(cafeInfo.getName(), cafeInfo.getExplain(), cafeInfo.getContactNumber(), cafeInfo.getAddress());
    }

    public BusinessHours getBusinessHours(){
        return BusinessHours.of(businessHours.getOperationTimeList());
    }

    public Cafe updateCafe(CafeInfo updateCafeInfo, BusinessHours updateBusinessHours){
        return new Cafe(updateCafeInfo, updateBusinessHours);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cafe cafe = (Cafe) o;
        return Objects.equals(cafeInfo, cafe.cafeInfo) &&
                Objects.equals(businessHours, cafe.businessHours);
    }
}
