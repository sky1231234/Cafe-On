package com.example.order.cafe.domain;

import com.example.order.cafe.errorMsg.OperationTimePerDayErrorMsg;
import com.example.order.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
@Entity
public class OperationTimePerDay extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private final Days days;

    @Embedded
    @JoinColumn(unique = true)
    private final OperationTime operationTime;

    @ManyToOne
    @JoinColumn(name = "cafeId")
    private Cafe cafe;

    private OperationTimePerDay(Days days, OperationTime operationTime, Cafe cafe){
        validation(operationTime);
        this.days = days;
        this.operationTime = operationTime;
        this.cafe = cafe;
    }

    public static OperationTimePerDay of(Days days, OperationTime operationTime, Cafe cafe){
        return new OperationTimePerDay(days, operationTime, cafe);
    }

    private OperationTimePerDay(Days days, OperationTime operationTime){
        validation(operationTime);
        this.days = days;
        this.operationTime = operationTime;
    }

    public static OperationTimePerDay of(Days days, OperationTime operationTime){
        return new OperationTimePerDay(days, operationTime);
    }

    public void addCafe(Cafe cafe){
        this.cafe = cafe;
    }

    public void validation(OperationTime operationTime){
        if(operationTime == null){
            throw new IllegalArgumentException(OperationTimePerDayErrorMsg.OPERATION_TIME_NON_NULL_ERROR_MSG.getValue());
        }
    }

}
