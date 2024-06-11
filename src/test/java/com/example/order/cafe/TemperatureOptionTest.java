package com.example.order.cafe;

import com.example.order.cafe.domain.CafeMenu;
import com.example.order.cafe.domain.MenuCategory;
import com.example.order.cafe.domain.TemperatureOption;
import com.example.order.cafe.domain.TemperatureType;
import com.example.order.cafe.errorMsg.TemperatureOptionErrorMsg;
import com.example.order.cafe.fixture.CafeMenuFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureOptionTest {

    @DisplayName("생성 : 정상")
    @Test
    public void create(){

        CafeMenu 메뉴 = CafeMenuFixture.카페메뉴_기본생성();
        TemperatureType 온도_타입 = TemperatureType.HOT;

        TemperatureOption 온도_옵션 = TemperatureOption.of(메뉴, 온도_타입);

        assertNotNull(온도_옵션);
    }

    @DisplayName("생성 예외 : 카페 메뉴 null")
    @Test
    public void create_error_menu_name_length() {

        TemperatureType temperatureType = TemperatureType.HOT;

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        TemperatureOption.of(null, temperatureType))
                .withMessage(TemperatureOptionErrorMsg.CAFE_MENU_NON_NULL_ERROR_MSG.getValue());

    }

    @DisplayName("생성 예외 : 온도 타입 유효하지 않을 때")
    @Test
    public void create_error_stock(){

        CafeMenu 메뉴 = CafeMenuFixture.카페메뉴_기본생성();

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        TemperatureOption.of(메뉴, null))
                .withMessage(TemperatureOptionErrorMsg.TEMPERATURE_TYPE_INVALID_ERROR_MSG.getValue());

    }

    @DisplayName("비교")
    @Test
    public void equal() {

        CafeMenu 첫번째_메뉴 = CafeMenuFixture.카페메뉴_기본생성();
        CafeMenu 두번째_메뉴 = CafeMenu.of(2L, "라떼", MenuCategory.COFFEE, "아메리카노", 10, 4500);
        TemperatureType 온도_옵션 = TemperatureType.ICE;

        TemperatureOption 옵션 = TemperatureOption.of(첫번째_메뉴, 온도_옵션);
        TemperatureOption 같은_옵션 = TemperatureOption.of(첫번째_메뉴, 온도_옵션);
        TemperatureOption 다른_옵션 = TemperatureOption.of(두번째_메뉴, 온도_옵션);

        assertEquals(옵션, 같은_옵션);
        assertNotEquals(옵션, 다른_옵션);

    }

}
