package com.example.order.member.service;

import com.example.order.member.domain.AuthType;
import com.example.order.member.domain.Member;
import com.example.order.member.dto.response.MemberInfoResponse;
import com.example.order.member.errorMsg.MemberErrorMsg;
import com.example.order.member.exception.MemberException;
import com.example.order.member.fixture.MemberFixture;
import com.example.order.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("MemberService 테스트")
public class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("정상 : 회원가입 테스트")
    public void signUp(){

        given(memberRepository.findByMemberId(MemberFixture.아이디)).willReturn(Optional.empty());

        memberService.signUp(MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름, MemberFixture.권한, MemberFixture.연락처);

        then(memberRepository).should(times(0)).findByMemberId(String.valueOf(any(Member.class)));
        then(memberRepository).should().save(any(Member.class));
    }

    @Test
    @DisplayName("예외 : 존재하는 아이디 회원가입 테스트")
    public void signUp_error_alreadyExist_id(){

        Member 존재하는_회원 = MemberFixture.회원_기본생성();
        given(memberRepository.findByMemberId(MemberFixture.아이디)).willReturn(Optional.of(존재하는_회원));

        assertThatRuntimeException()
                .isThrownBy(() -> memberService.signUp(MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름, MemberFixture.권한, MemberFixture.연락처))
                        .withMessage(MemberException.ALREADY_EXIST_MEMBER_ID_EXCEPTION.toString());

        then(memberRepository).should(times(1)).findByMemberId(존재하는_회원.getMemberId());
        then(memberRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    @DisplayName("정상 : 회원 정보 조회")
    public void getMemberInfoTest(){

        Member 회원 = MemberFixture.회원_기본생성();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(회원));

        MemberInfoResponse memberInfoResponse = memberService.getMemberInfo(anyLong());

        assertThat(memberInfoResponse.getName()).isEqualTo(회원.getName());
        then(memberRepository).should(times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("예외 : 회원 정보 없을 때")
    public void signUp_error_getMemberInfo(){

        given(memberRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatRuntimeException()
                .isThrownBy(() -> memberService.getMemberInfo(anyLong()))
                .isInstanceOf(NoSuchElementException.class);

    }

    @Test
    @DisplayName("정상 : 회원 정보 전체 조회")
    public void getMemberInfoTestList(){

        Member 회원 = MemberFixture.회원_기본생성();
        given(memberRepository.findAll()).willReturn(Arrays.asList(회원,회원));

        List<MemberInfoResponse> memberInfoResponseList = memberService.getMemberInfoList();

        assertThat(memberInfoResponseList.size()).isEqualTo(2);
        assertThat(memberInfoResponseList.get(0).getName()).isEqualTo(회원.getName());

    }

    @Test
    @DisplayName("예외 : 회원 정보 리스트 사이즈 0 일때")
    public void signUp_error_getMemberInfoTestList(){

        given(memberRepository.findAll()).willReturn(Collections.emptyList());

        List<MemberInfoResponse> memberInfoList = memberService.getMemberInfoList();

        assertThat(memberInfoList).isEmpty();
    }

    @Test
    @DisplayName("정상 : 회원 정보 수정")
    public void updateMemberInfo(){

        Member 회원 = MemberFixture.회원_기본생성();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(회원));
        String 변경_비밀번호  = "updatePassword";
        AuthType 변경_권한 = AuthType.CAFE_OWNER;
        String 변경_연락처 = "111-2222-2222";

        memberService.updateMemberInfo(anyLong(), 변경_비밀번호, 변경_권한, 변경_연락처);

        Member 변경_회원 = Member.of(회원.getMemberId(), 변경_비밀번호, 회원.getName(), 변경_권한, 변경_연락처);
        then(memberRepository).should(times(1)).save(변경_회원);

    }

    @Test
    @DisplayName("예외 : 회원 정보 수정 - 회원 없을때")
    public void updateMemberInfo_error_memberNotFound() {

        given(memberRepository.findById(anyLong())).willReturn(Optional.empty());
        String 변경_비밀번호  = "updatePassword";
        AuthType 변경_권한 = AuthType.CAFE_OWNER;
        String 변경_연락처 = "111-2222-2222";

        assertThatRuntimeException()
                .isThrownBy(() -> memberService.updateMemberInfo(anyLong(), 변경_비밀번호, 변경_권한, 변경_연락처))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("예외 : 회원 정보 수정 - 비밀번호가 유효하지 않을때")
    public void updateMemberInfo_error_invalidPassword() {

        Member 회원 = MemberFixture.회원_기본생성();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(회원));
        String 널_비밀번호  = null;
        AuthType 변경_권한 = AuthType.CAFE_OWNER;
        String 변경_연락처 = "111-2222-2222";

        assertThatNullPointerException()
                .isThrownBy(() -> memberService.updateMemberInfo(anyLong(), 널_비밀번호, 변경_권한, 변경_연락처))
                .withMessage(MemberErrorMsg.MEMBER_PASSWORD_NULL_ERROR_MESSAGE.getValue());
    }

    @Test
    @DisplayName("정상 : 회원 정보 탈퇴")
    public void deleteMember(){

        Member 회원 = MemberFixture.회원_기본생성();
        given(memberRepository.findById(anyLong())).willReturn(Optional.of(회원));

        memberService.deleteMember(anyLong());

        then(memberRepository).should(times(1)).delete(회원);

    }
}