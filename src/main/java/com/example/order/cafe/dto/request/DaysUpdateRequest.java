package com.example.order.cafe.dto.request;

import com.example.order.cafe.domain.Days;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public class DaysUpdateRequest {

    @NotBlank(message = "요일은 필수 입력 값입니다.")
    private final Days days;

    public DaysUpdateRequest(Days days){
        this.days = days;
    }

}
