package com.example.order.cafe.errorMsg;

public enum TemperatureOptionErrorMsg {
    CAFE_MENU_NON_NULL_ERROR_MSG("TO101","카페 메뉴의 값은 null일 수 없습니다."),
    TEMPERATURE_TYPE_INVALID_ERROR_MSG("TO101","카페 메뉴의 값은 null일 수 없습니다."),
    ;

    private final String code;
    private final String value;

    TemperatureOptionErrorMsg(String code, String value){
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
