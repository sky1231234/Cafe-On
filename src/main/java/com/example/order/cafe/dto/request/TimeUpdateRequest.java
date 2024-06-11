package com.example.order.cafe.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public class TimeUpdateRequest {

    @Min(0) @Max(23)
    private final int hour;
    @Min(0) @Max(59)
    private final int minute;

    public TimeUpdateRequest(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
}
