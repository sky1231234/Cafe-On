package com.example.order.cafe.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public class OperationTimeUpdateRequest {

    @NotNull
    private final TimeUpdateRequest open;
    @NotNull
    private final TimeUpdateRequest close;

    public OperationTimeUpdateRequest(TimeUpdateRequest open, TimeUpdateRequest close){
        this.open = open;
        this.close = close;
    }

}
