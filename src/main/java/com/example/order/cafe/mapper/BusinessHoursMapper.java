package com.example.order.cafe.mapper;

import com.example.order.cafe.domain.BusinessHours;
import com.example.order.cafe.domain.OperationTimePerDay;
import com.example.order.cafe.dto.request.BusinessHoursRequest;
import com.example.order.cafe.dto.request.BusinessHoursUpdateRequest;
import com.example.order.cafe.dto.request.OperationTimePerDayRequest;
import com.example.order.cafe.dto.request.OperationTimePerDayUpdateRequest;
import com.example.order.cafe.dto.response.BusinessHoursResponse;
import com.example.order.cafe.dto.response.OperationTimePerDayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class  BusinessHoursMapper {

    private final OperationTimePerDayMapper operationTimePerDayMapper;

    public BusinessHours toBusinessHours(BusinessHoursRequest businessHoursRequest){

        List<OperationTimePerDay> operationTimeList =
                toOperationTimePerDayList_forCreate( businessHoursRequest.getOperationTimePerDayList() );

        return BusinessHours.of(operationTimeList);
    }

    public List<OperationTimePerDay> toOperationTimePerDayList_forCreate(List<OperationTimePerDayRequest> operationTimePerDayRequestList) {
        if ( operationTimePerDayRequestList == null ) {
            return null;
        }

        List<OperationTimePerDay> operationTimePerDayList = new ArrayList<>( operationTimePerDayRequestList.size() );

        for ( OperationTimePerDayRequest operationTimePerDayRequest : operationTimePerDayRequestList ) {
            OperationTimePerDay operationTimePerDay = operationTimePerDayMapper.toOperationTimePerDay(operationTimePerDayRequest);
            operationTimePerDayList.add( operationTimePerDay );
        }

        return operationTimePerDayList;
    }

    public BusinessHours toBusinessHours(BusinessHoursUpdateRequest businessHoursUpdateRequest){

        List<OperationTimePerDay> operationTimeList =
                toOperationTimePerDayList_forUpdate( businessHoursUpdateRequest.getOperationTimePerDayList() );

        return BusinessHours.of(operationTimeList);
    }

    public List<OperationTimePerDay> toOperationTimePerDayList_forUpdate(List<OperationTimePerDayUpdateRequest> operationTimePerDayUpdateRequestList) {
        if ( operationTimePerDayUpdateRequestList == null ) {
            return null;
        }

        List<OperationTimePerDay> operationTimePerDayList = new ArrayList<>( operationTimePerDayUpdateRequestList.size() );

        for ( OperationTimePerDayUpdateRequest operationTimePerDayRequest : operationTimePerDayUpdateRequestList ) {
            OperationTimePerDay operationTimePerDay = operationTimePerDayMapper.toOperationTimePerDay(operationTimePerDayRequest);
            operationTimePerDayList.add( operationTimePerDay );
        }

        return operationTimePerDayList;
    }


    public BusinessHoursResponse toBusinessHoursResponse(BusinessHours businessHours){

        List<OperationTimePerDayResponse> operationTimePerDayRequestList =
                toOperationTimePerDayRequestList(businessHours.getOperationTimePerDayList());

        return new BusinessHoursResponse(operationTimePerDayRequestList);
    }

    public List<OperationTimePerDayResponse> toOperationTimePerDayRequestList(List<OperationTimePerDay> operationTimePerDayList) {
        if ( operationTimePerDayList == null ) {
            return null;
        }

        List<OperationTimePerDayResponse> operationTimePerDayResponseList = new ArrayList<>( operationTimePerDayList.size() );

        for ( OperationTimePerDay operationTimePerDay : operationTimePerDayList ) {
            OperationTimePerDayResponse operationTimePerDayResponse = operationTimePerDayMapper.toOperationTimePerDayResponse(operationTimePerDay);
            operationTimePerDayResponseList.add( operationTimePerDayResponse );
        }

        return operationTimePerDayResponseList;
    }

}
