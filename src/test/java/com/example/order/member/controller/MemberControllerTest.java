package com.example.order.member.controller;

import com.example.order.member.domain.AuthType;
import com.example.order.member.domain.Grade;
import com.example.order.member.domain.Member;
import com.example.order.member.dto.request.SignUpRequest;
import com.example.order.member.dto.request.UpdateMemberRequest;
import com.example.order.member.fixture.MemberFixture;
import com.example.order.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @SpyBean
    MemberRepository memberRepository;

    @Test
    @DisplayName("정상 : 회원가입")
    void signupTest() throws Exception {

        SignUpRequest signUpRequest = MemberFixture.회원가입_REQEUST_DTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/members/signup")
                        .content(objectMapper.writeValueAsString(signUpRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andDo(print());

        assertThat(memberRepository.findByMemberId(MemberFixture.아이디).get()
                .getPhoneNum()).isEqualTo(MemberFixture.연락처);

    }

    @Test
    @DisplayName("정상 : 회원 정보 조회")
    void getMemberInfoTest() throws Exception {

        when(memberRepository.findById(1L)).thenReturn(Optional.of(MemberFixture.회원_기본생성()));

        mockMvc.perform(get("/members/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(MemberFixture.아이디))
                .andDo(print());

    }

    @Test
    @DisplayName("정상 : 전체 회원 정보 조회")
    void getMemberInfoListTest() throws Exception {

        String 두번째_아이디 ="123id";
        String 두번째_비밀번호 = "password";
        String 두번째_이름 = "회원정보";
        AuthType 두번째_권한 = AuthType.CAFE_OWNER;
        String 두번째_연락처 = "22222222222";
        Grade 두번째_등급 = Grade.VIP;

        Member 첫번째_회원 = MemberFixture.회원_기본생성();
        Member 두번째_회원 = Member.of(두번째_아이디, 두번째_비밀번호, 두번째_이름, 두번째_권한, 두번째_연락처, 두번째_등급);

        List<Member> 회원_리스트 = List.of(첫번째_회원, 두번째_회원);
        when(memberRepository.findAll()).thenReturn(회원_리스트);

        mockMvc.perform(get("/members/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].memberId").value(MemberFixture.아이디))
                .andExpect(jsonPath("$[1].memberId").value(두번째_아이디));
    }

    @Test
    @DisplayName("정상 : 회원 정보 수정")
    void updateMemberInfoTest() throws Exception {

        String 변경_비밀번호 = "pass1234";
        AuthType 변경_권한 = AuthType.CAFE_OWNER;
        String 변경_연락처 = "33333333333";

        when(memberRepository.findById(1L)).thenReturn(Optional.of(MemberFixture.회원_기본생성()));

        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest(변경_비밀번호, 변경_권한, 변경_연락처);

        mockMvc.perform(patch("/members/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updateMemberRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("정상 : 회원 정보 삭제")
    void deleteMemberTest() throws Exception {

        when(memberRepository.findById(1L)).thenReturn(Optional.of(MemberFixture.회원_기본생성()));

        mockMvc.perform(delete("/members/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}
