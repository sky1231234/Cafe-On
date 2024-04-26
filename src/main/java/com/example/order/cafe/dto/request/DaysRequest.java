package com.example.order.cafe.dto.request;

import com.example.order.cafe.domain.Days;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DaysRequest {

    @NotBlank(message = "요일은 필수 입력 값입니다.")
    private final Days dayOfWeek;

    public DaysRequest(Days dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

}
