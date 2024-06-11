package com.example.order.cafe.mapper;

import com.example.order.cafe.domain.OperationTime;
import com.example.order.cafe.domain.Time;
import com.example.order.cafe.dto.request.OperationTimeRequest;
import com.example.order.cafe.dto.request.OperationTimeUpdateRequest;
import com.example.order.cafe.dto.request.TimeRequest;
import com.example.order.cafe.dto.request.TimeUpdateRequest;
import com.example.order.cafe.dto.response.OperationTimeResponse;
import com.example.order.cafe.dto.response.TimeResponse;
import org.springframework.stereotype.Component;


@Component
public class OperationTimeMapper {

    public OperationTime toOperationTime(OperationTimeRequest operationTimeRequest){
            if ( operationTimeRequest == null ) {
                return null;
            }
        TimeRequest open = operationTimeRequest.getOpen();
        TimeRequest close = operationTimeRequest.getClose();

        return OperationTime.of(toTime(open), toTime(close));
    }


    public Time toTime(TimeRequest timeRequest){
        if ( timeRequest == null ) {
            return null;
        }

        return Time.of(timeRequest.getHour(), timeRequest.getMinute());

    }

    public OperationTime toOperationTime(OperationTimeUpdateRequest operationTimeUpdateRequest){
        if ( operationTimeUpdateRequest == null ) {
            return null;
        }
        TimeUpdateRequest open = operationTimeUpdateRequest.getOpen();
        TimeUpdateRequest close = operationTimeUpdateRequest.getClose();

        return OperationTime.of(toTime(open), toTime(close));
    }


    public Time toTime(TimeUpdateRequest timeUpdateRequest){
        if ( timeUpdateRequest == null ) {
            return null;
        }

        return Time.of(timeUpdateRequest.getHour(), timeUpdateRequest.getMinute());

    }

    public OperationTimeResponse toOperationTimeResponse(OperationTime operationTime){
        if ( operationTime == null ) {
            return null;
        }
        Time open = operationTime.getOpen();
        Time close = operationTime.getClose();

        return new OperationTimeResponse(toTime(open), toTime(close));
    }


    public TimeResponse toTime(Time time){
        if ( time == null ) {
            return null;
        }

        return new TimeResponse(time.getHour(), time.getMinute());

    }
}
