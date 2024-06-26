package com.example.order.cafe.errorMsg;

public enum OperationTimePerDayErrorMsg {
    OPERATION_TIME_NON_NULL_ERROR_MSG("OD101","OperationTime 값은 null일 수 없습니다."),
    ;

    private final String code;
    private final String value;

    OperationTimePerDayErrorMsg(String code, String value){
        this.code = code;
        this.value = value;
    }

    public String getCode(){
        return this.value;
    }
    public String getValue(){
        return this.value;
    }

}
