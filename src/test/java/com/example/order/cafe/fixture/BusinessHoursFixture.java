package com.example.order.cafe.fixture;

import com.example.order.cafe.domain.*;
import com.example.order.cafe.dto.request.*;

import java.util.ArrayList;
import java.util.List;

public class BusinessHoursFixture {

    public static final OperationTime 평일_운영시간 = OperationTime.of(Time.of(9, 0), Time.of(17, 0));
    public static final OperationTime 주말_운영시간 = OperationTime.of(Time.of(0, 0), Time.of(0, 0));

    public static List<OperationTimePerDay> 운영시간_리스트_월화수목금토(){

        OperationTimePerDay 월요일 =  OperationTimePerDay.of(Days.MONDAY, 평일_운영시간);
        OperationTimePerDay 화요일 =  OperationTimePerDay.of(Days.TUESDAY, 평일_운영시간);
        OperationTimePerDay 수요일 =  OperationTimePerDay.of(Days.WEDNESDAY, 평일_운영시간);
        OperationTimePerDay 목요일 =  OperationTimePerDay.of(Days.THURSDAY, 평일_운영시간);
        OperationTimePerDay 금요일 =  OperationTimePerDay.of(Days.FRIDAY, 평일_운영시간);
        OperationTimePerDay 토요일 =  OperationTimePerDay.of(Days.SATURDAY, 주말_운영시간);

        List<OperationTimePerDay> 운영시간_리스트 = new ArrayList<>();

        운영시간_리스트.add(월요일);
        운영시간_리스트.add(화요일);
        운영시간_리스트.add(수요일);
        운영시간_리스트.add(목요일);
        운영시간_리스트.add(금요일);
        운영시간_리스트.add(토요일);

        return 운영시간_리스트;
    }

    public static BusinessHours 운영시간_전체기본생성(){

        List<OperationTimePerDay> 운영시간_리스트 = 운영시간_리스트_월화수목금토();
        OperationTimePerDay 일요일 =  OperationTimePerDay.of(Days.SUNDAY, 주말_운영시간);
        운영시간_리스트.add(일요일);

        return BusinessHours.of(운영시간_리스트);
    }

    public static List<OperationTimePerDayRequest> 운영시간_리스트_REQUEST_DTO(){
        OperationTimeRequest 평일_운영시간 = new OperationTimeRequest(new TimeRequest(9, 0), new TimeRequest(17, 0));
        OperationTimeRequest 주말_운영시간 = new OperationTimeRequest(new TimeRequest(0, 0), new TimeRequest(0, 0));

        OperationTimePerDayRequest 월요일 =  new OperationTimePerDayRequest(new DaysRequest(Days.MONDAY), 평일_운영시간);
        OperationTimePerDayRequest 화요일 =  new OperationTimePerDayRequest(new DaysRequest(Days.TUESDAY), 평일_운영시간);
        OperationTimePerDayRequest 수요일 =  new OperationTimePerDayRequest(new DaysRequest(Days.WEDNESDAY), 평일_운영시간);
        OperationTimePerDayRequest 목요일 =  new OperationTimePerDayRequest(new DaysRequest(Days.THURSDAY), 평일_운영시간);
        OperationTimePerDayRequest 금요일 =  new OperationTimePerDayRequest(new DaysRequest(Days.FRIDAY), 평일_운영시간);
        OperationTimePerDayRequest 토요일 =  new OperationTimePerDayRequest(new DaysRequest(Days.SATURDAY), 주말_운영시간);
        OperationTimePerDayRequest 일요일 =  new OperationTimePerDayRequest(new DaysRequest(Days.SUNDAY), 주말_운영시간);

        List<OperationTimePerDayRequest> 운영시간_리스트 = new ArrayList<>();

        운영시간_리스트.add(월요일);
        운영시간_리스트.add(화요일);
        운영시간_리스트.add(수요일);
        운영시간_리스트.add(목요일);
        운영시간_리스트.add(금요일);
        운영시간_리스트.add(토요일);
        운영시간_리스트.add(일요일);

        return 운영시간_리스트;
    }

    public static List<OperationTimePerDayUpdateRequest> 운영시간_리스트_UPDATE_REQUEST_DTO(){
        OperationTimeUpdateRequest 평일_운영시간 = new OperationTimeUpdateRequest(new TimeUpdateRequest(9, 0), new TimeUpdateRequest(17, 0));
        OperationTimeUpdateRequest 주말_운영시간 = new OperationTimeUpdateRequest(new TimeUpdateRequest(0, 0), new TimeUpdateRequest(0, 0));

        OperationTimePerDayUpdateRequest 월요일 =  new OperationTimePerDayUpdateRequest(new DaysUpdateRequest(Days.MONDAY), 주말_운영시간);
        OperationTimePerDayUpdateRequest 화요일 =  new OperationTimePerDayUpdateRequest(new DaysUpdateRequest(Days.TUESDAY), 주말_운영시간);
        OperationTimePerDayUpdateRequest 수요일 =  new OperationTimePerDayUpdateRequest(new DaysUpdateRequest(Days.WEDNESDAY), 주말_운영시간);
        OperationTimePerDayUpdateRequest 목요일 =  new OperationTimePerDayUpdateRequest(new DaysUpdateRequest(Days.THURSDAY), 주말_운영시간);
        OperationTimePerDayUpdateRequest 금요일 =  new OperationTimePerDayUpdateRequest(new DaysUpdateRequest(Days.FRIDAY), 주말_운영시간);
        OperationTimePerDayUpdateRequest 토요일 =  new OperationTimePerDayUpdateRequest(new DaysUpdateRequest(Days.SATURDAY), 평일_운영시간);
        OperationTimePerDayUpdateRequest 일요일 =  new OperationTimePerDayUpdateRequest(new DaysUpdateRequest(Days.SUNDAY), 평일_운영시간);

        List<OperationTimePerDayUpdateRequest> 운영시간_리스트 = new ArrayList<>();

        운영시간_리스트.add(월요일);
        운영시간_리스트.add(화요일);
        운영시간_리스트.add(수요일);
        운영시간_리스트.add(목요일);
        운영시간_리스트.add(금요일);
        운영시간_리스트.add(토요일);
        운영시간_리스트.add(일요일);

        return 운영시간_리스트;
    }

    public static OperationTimeRequest 운영시간_REQUEST_DTO(TimeRequest open, TimeRequest close){
        return new OperationTimeRequest(open, close);
    }


    public static BusinessHoursRequest 카페운영시간_REQUEST_DTO(){
        return new BusinessHoursRequest(운영시간_리스트_REQUEST_DTO());
    }
    public static BusinessHoursUpdateRequest 카페운영시간_UPDATE_REQUEST_DTO(){
        return new BusinessHoursUpdateRequest(운영시간_리스트_UPDATE_REQUEST_DTO());
    }
}
