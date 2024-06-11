package com.example.order.cafe.fixture;

import com.example.order.cafe.domain.Cafe;
import com.example.order.cafe.dto.request.CafeCreateRequest;

public class CafeFixture {

    public static Cafe 카페_기본생성(){
        return Cafe.of(CafeInfoFixture.카페프로필_기본생성(),
                BusinessHoursFixture.운영시간_전체기본생성());
    }

    public static CafeCreateRequest 카페_등록_REQUEST_DTO(){
        return new CafeCreateRequest(CafeInfoFixture.카페프로필_REQUEST_DTO(),
                BusinessHoursFixture.카페운영시간_REQUEST_DTO());
    }


}
