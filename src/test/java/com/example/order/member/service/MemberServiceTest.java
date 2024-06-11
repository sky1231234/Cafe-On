package com.example.order.member.service;

import com.example.order.member.domain.AuthType;
import com.example.order.member.domain.Grade;
import com.example.order.member.domain.Member;
import com.example.order.member.dto.response.MemberResponse;
import com.example.order.member.errorCode.MemberErrorCode;
import com.example.order.member.fixture.MemberFixture;
import com.example.order.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DisplayName("MemberService 테스트")
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("정상 : 회원가입 테스트")
    public void signUp(){

        memberService.signUp(MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름, MemberFixture.권한, MemberFixture.연락처, MemberFixture.등급);

        Optional<Member> 회원가입_회원 = memberRepository.findByMemberId(MemberFixture.아이디);

        assertThat(회원가입_회원.get().getMemberId()).isEqualTo(MemberFixture.아이디);
        assertThat(memberRepository.findAll().size()).isEqualTo(1);

    }

    @Test
    @DisplayName("예외 : 존재하는 아이디 회원가입 테스트")
    public void signUp_error_alreadyExist_id(){

        memberService.signUp(MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름, MemberFixture.권한, MemberFixture.연락처, MemberFixture.등급);

        assertThatRuntimeException()
                .isThrownBy(() -> memberService.signUp(MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름, MemberFixture.권한, MemberFixture.연락처, MemberFixture.등급))
                        .withMessage(MemberErrorCode.ALREADY_EXIST_MEMBER_ID_EXCEPTION.getValue());

    }

    @Test
    @DisplayName("정상 : 회원 정보 조회")
    public void getMemberInfoTest(){

        Member 첫번째_회원 = memberService.signUp(
                MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름,
                MemberFixture.권한, MemberFixture.연락처, MemberFixture.등급);

        MemberResponse memberInfoResponse = memberService.getMember(첫번째_회원.getId());

        assertThat(memberInfoResponse.getName()).isEqualTo(첫번째_회원.getName());
    }

    @Test
    @DisplayName("예외 : 회원 정보 없을 때")
    public void signUp_error_getMember(){

        assertThatRuntimeException()
                .isThrownBy(() -> memberService.getMember(anyLong()))
                .isInstanceOf(NoSuchElementException.class);

    }

    @Test
    @DisplayName("정상 : 회원 정보 전체 조회")
    public void getMemberTestList(){


        Member 첫번째_회원 = memberService.signUp(
                MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름,
                MemberFixture.권한, MemberFixture.연락처, MemberFixture.등급);

        String 두번째_회원_아이디 = "second12";
        String 두번째_회원_비밀번호 = "secondPW";
        String 두번째_회원_이름 = "두번째회원";
        AuthType 두번째_회원_권한 = AuthType.ADMIN;
        String 두번째_회원_연락처 = "0109999999";
        Grade 두번째_회원_등급 = Grade.SILVER;

        Member 두번째_회원 = memberService.signUp(두번째_회원_아이디,두번째_회원_비밀번호, 두번째_회원_이름, 두번째_회원_권한, 두번째_회원_연락처, 두번째_회원_등급);


        List<MemberResponse> 회원정보_리스트 = memberService.getMemberList();

        assertThat(회원정보_리스트.size()).isEqualTo(2);
        assertThat(회원정보_리스트.get(1).getAuthType()).isEqualTo(두번째_회원.getAuthType());

    }

    @Test
    @DisplayName("예외 : 회원 정보 리스트 사이즈 0 일때")
    public void signUp_error_getMemberTestList(){

        List<MemberResponse> memberInfoList = memberService.getMemberList();

        assertThat(memberInfoList).isEmpty();
    }

    @Test
    @DisplayName("정상 : 회원 정보 수정")
    public void updateMember(){

        Member 첫번째_회원 = memberService.signUp(
                MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름,
                MemberFixture.권한, MemberFixture.연락처, MemberFixture.등급);

        String 변경_비밀번호  = "updatePassword";
        AuthType 변경_권한 = AuthType.CAFE_OWNER;
        String 변경_연락처 = "11122222222";

        memberService.updateMember(첫번째_회원.getId(), 변경_비밀번호, 변경_권한, 변경_연락처);

        Member 변경된_회원 = memberRepository.findById(첫번째_회원.getId())
                .orElseThrow(NoSuchElementException::new);

        assertThat(변경된_회원.getPassword()).isEqualTo(변경_비밀번호);
        assertThat(변경된_회원.getAuthType()).isEqualTo(변경_권한);
        assertThat(변경된_회원.getPhoneNum()).isEqualTo(변경_연락처);

    }

    @Test
    @DisplayName("예외 : 회원 정보 수정 - id 값 없을때")
    public void updateMember_error_memberNotFound() {

        long 확인할_회원_id = 1L;
        String 변경_비밀번호  = "updatePassword";
        AuthType 변경_권한 = AuthType.CAFE_OWNER;
        String 변경_연락처 = "111-2222-2222";

        assertThatRuntimeException()
                .isThrownBy(() -> memberService.updateMember(확인할_회원_id, 변경_비밀번호, 변경_권한, 변경_연락처))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("정상 : 회원 정보 탈퇴")
    public void deleteMember(){

        Member 첫번째_회원 = memberService.signUp(
                MemberFixture.아이디, MemberFixture.비밀번호, MemberFixture.이름,
                MemberFixture.권한, MemberFixture.연락처, MemberFixture.등급);

        memberService.deleteMember(첫번째_회원.getId());

        assertThat(memberRepository.findAll()).isEmpty();


    }
}
