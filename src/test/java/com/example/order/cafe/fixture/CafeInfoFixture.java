package com.example.order.cafe.fixture;

import com.example.order.cafe.domain.CafeInfo;
import com.example.order.cafe.dto.request.CafeInfoCreateRequest;

public class CafeInfoFixture {

    public static final String 카페명 = "id123";
    public static final String 설명 = "저희카페는 강남에 위치해있습니다.";
    public static final String 연락처 = "02-111-1111";
    public static final String 주소 = "서울시 강남구 1010동";

    public static CafeInfo 카페프로필_기본생성(){
        return CafeInfo.of(카페명, 설명, 연락처, 주소);
    }

    public static CafeInfoCreateRequest 카페프로필_REQUEST_DTO(){
        return new CafeInfoCreateRequest(카페명, 설명, 연락처, 주소);
    }



}
