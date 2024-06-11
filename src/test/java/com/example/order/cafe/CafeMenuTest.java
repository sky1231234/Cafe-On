package com.example.order.cafe;

import com.example.order.cafe.domain.CafeMenu;
import com.example.order.cafe.errorMsg.CafeMenuErrorMsg;
import com.example.order.cafe.fixture.CafeMenuFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CafeMenuTest {

    @DisplayName("생성 : 정상")
    @Test
    public void create(){

        CafeMenu 카페메뉴 = CafeMenuFixture.카페메뉴_기본생성();

        assertThat(카페메뉴).isNotNull();
        assertThat(카페메뉴.isMenuName(CafeMenuFixture.메뉴명)).isTrue();
    }

    @DisplayName("생성 예외 : 메뉴명 - 최소 1자 이상")
    @Test
    public void create_error_menu_name_length() {

        String 빈_메뉴명 = "";

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        CafeMenu.of(CafeMenuFixture.카페ID,
                                빈_메뉴명,
                                CafeMenuFixture.메뉴카테고리,
                                CafeMenuFixture.설명,
                                CafeMenuFixture.재고,
                                CafeMenuFixture.가격))
                .withMessage(CafeMenuErrorMsg.MENU_NAME_LENGTH_ERROR_MESSAGE.getValue());
    }


    @DisplayName("생성 예외 : 재고 - 0개 이상")
    @Test
    public void create_error_stock(){

        int 음수_재고 = -1;

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        CafeMenu.of(CafeMenuFixture.카페ID,
                                CafeMenuFixture.메뉴명,
                                CafeMenuFixture.메뉴카테고리,
                                CafeMenuFixture.설명,
                                음수_재고,
                                CafeMenuFixture.가격))
                .withMessage(CafeMenuErrorMsg.STOCK_UNDER_ZERO_ERROR_MESSAGE.getValue());
    }

    @DisplayName("생성 예외 : 가격 - 0원 이상")
    @Test
    public void create_error_price(){

        int 음수_가격 = -1;

        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        CafeMenu.of(CafeMenuFixture.카페ID,
                                CafeMenuFixture.메뉴명,
                                CafeMenuFixture.메뉴카테고리,
                                CafeMenuFixture.설명,
                                CafeMenuFixture.재고,
                                음수_가격))
                .withMessage(CafeMenuErrorMsg.PRICE_UNDER_ZERO_ERROR_MESSAGE.getValue());

    }


    @DisplayName("생성 : 객체 비교 - 설명, 재고만 다를 때")
    @Test
    public void create_equal() {

        String 다른_설명 = "산미가 있고 시원합니다.";
        int 다른_재고 = 0;

        CafeMenu 첫번째_카페메뉴 = CafeMenuFixture.카페메뉴_기본생성();
        CafeMenu 두번째_카페메뉴 = CafeMenu.of(CafeMenuFixture.카페ID,
                CafeMenuFixture.메뉴명,
                CafeMenuFixture.메뉴카테고리,
                다른_설명,
                다른_재고,
                CafeMenuFixture.가격);

        assertEquals(첫번째_카페메뉴, 두번째_카페메뉴);

    }

}
