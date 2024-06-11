package com.example.order.cafe.service;

import com.example.order.cafe.domain.*;
import com.example.order.cafe.dto.request.*;
import com.example.order.cafe.dto.response.CafeResponse;
import com.example.order.cafe.fixture.BusinessHoursFixture;
import com.example.order.cafe.fixture.CafeFixture;
import com.example.order.cafe.fixture.CafeInfoFixture;
import com.example.order.cafe.repository.CafeRepository;
import com.example.order.cafe.repository.OperationTimePerDayRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DisplayName("CafeService 테스트")
public class CafeServiceTest {

    @Autowired
    private CafeService cafeService;

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private OperationTimePerDayRepository operationTimePerDayRepository;

    @Test
    @DisplayName("정상 : 카페 등록")
    public void registerCafeTest(){

        CafeCreateRequest 카페_request = CafeFixture.카페_등록_REQUEST_DTO();

        Cafe 카페 = cafeService.registerCafe(카페_request);

        Optional<Cafe> 등록_카페 = cafeRepository.findById(카페.getId());
        List<OperationTimePerDay> 등록_카페_운영시간 = operationTimePerDayRepository.findByCafeId(카페.getId());

        assertNotNull(등록_카페);
        assertThat(등록_카페.get().getCafeInfo().getAddress()).isEqualTo(CafeInfoFixture.주소);
        assertThat(등록_카페.get().getBusinessHours().getOperationTimeList()).isEqualTo(등록_카페_운영시간);

    }

    @Test
    @DisplayName("정상 : 카페 수정")
    public void updateCafeTest(){

        cafeService.registerCafe(CafeFixture.카페_등록_REQUEST_DTO());

        long 카페_ID = 1L;

        String 수정_카페명 = "카페명수정";
        String 수정_설명 = "일요일은 휴무입니다";
        String 수정_연락처 = "031-222-2222";
        String 수정_주소 = "경기도 용인시";

        CafeInfoUpdateRequest 카페_프로필_수정_request = new CafeInfoUpdateRequest(수정_카페명, 수정_설명, 수정_연락처, 수정_주소);
        CafeUpdateRequest 카페_수정_request = new CafeUpdateRequest(카페_프로필_수정_request,
                BusinessHoursFixture.카페운영시간_UPDATE_REQUEST_DTO());


        cafeService.updateCafe(카페_ID, 카페_수정_request);

        Optional<Cafe> 수정_카페 = cafeRepository.findById(카페_ID);

        assertThat(수정_카페.get().getCafeInfo().getAddress()).isEqualTo(수정_주소);

    }

    @Test
    @DisplayName("정상 : 카페 삭제")
    public void deleteCafeTest(){

        CafeCreateRequest cafeCreateRequest = CafeFixture.카페_등록_REQUEST_DTO();
        Cafe 저장된_카페 = cafeService.registerCafe(cafeCreateRequest);

        cafeService.deleteCafe(저장된_카페.getId());

        Optional<Cafe> 카페_확인 = cafeRepository.findById(저장된_카페.getId());

        assertFalse(카페_확인.isPresent());

    }

    @Test
    @DisplayName("정상 : 카페 상세 조회")
    public void getCafeByIdTest(){

        int 목요일_번호  = 3;
        CafeCreateRequest 카페_등록_REQUEST = CafeFixture.카페_등록_REQUEST_DTO();
        Cafe 저장된_카페 = cafeService.registerCafe(카페_등록_REQUEST);

        long 카페_ID = 1L;

        CafeResponse 카페_RESPONSE = cafeService.getCafeById(카페_ID);

        assertEquals(카페_RESPONSE.getCafeInfo().getName(), 저장된_카페.getCafeInfo().getName());
        assertEquals(카페_RESPONSE.getBusinessHours().getOperationTimeList().get(목요일_번호).getDays().getValue(),
                저장된_카페.getBusinessHours().getOperationTimeList().get(목요일_번호).getDays().getValue());

    }

    @Test
    @DisplayName("정상 : 카페 상세 조회")
    public void error_check_existCafeTest(){

        long 카페_ID = 1L;

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> cafeService.check_existCafe(카페_ID));
    }

}
