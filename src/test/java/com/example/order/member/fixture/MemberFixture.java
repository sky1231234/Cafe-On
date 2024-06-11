package com.example.order.member.fixture;

import com.example.order.member.domain.AuthType;
import com.example.order.member.domain.Grade;
import com.example.order.member.domain.Member;
import com.example.order.member.dto.request.SignUpRequest;

public class MemberFixture {

    public static final String 아이디 = "id123";
    public static final String 비밀번호 = "password";
    public static final String 이름 = "스프링";
    public static final AuthType 권한 = AuthType.USER;
    public static final String 연락처 = "01011111111";
    public static final Grade 등급 = Grade.GOLD;

    public static Member 회원_기본생성(){
        return Member.of(아이디, 비밀번호, 이름, 권한, 연락처, 등급);
    }

    public static SignUpRequest 회원가입_REQEUST_DTO(){
        return new SignUpRequest(아이디, 비밀번호, 이름, 권한, 연락처, 등급);
    }

}
