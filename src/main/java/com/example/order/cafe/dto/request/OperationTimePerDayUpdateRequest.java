package com.example.order.cafe.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public class OperationTimePerDayUpdateRequest {

    @NotNull
    private final DaysUpdateRequest days;
    @NotNull
    private final OperationTimeUpdateRequest operationTime;

    public OperationTimePerDayUpdateRequest(DaysUpdateRequest days, OperationTimeUpdateRequest operationTime){
        this.days = days;
        this.operationTime = operationTime;
    }

}
