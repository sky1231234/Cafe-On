package com.example.order.cafe.dto.request;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(force = true)
@Getter
public class BusinessHoursUpdateRequest {

    @Size(min = 7, max = 7)
    private final List<OperationTimePerDayUpdateRequest> operationTimePerDayList;

    public BusinessHoursUpdateRequest(List<OperationTimePerDayUpdateRequest> operationTimePerDayList){
        this.operationTimePerDayList = operationTimePerDayList;
    }

}
