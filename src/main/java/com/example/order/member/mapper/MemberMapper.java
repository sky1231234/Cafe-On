package com.example.order.member.mapper;

import com.example.order.member.domain.Member;
import com.example.order.member.dto.response.MemberResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberMapper {

    public MemberResponse toMemberResponse(Member member){
        return new MemberResponse(member.getMemberId(),
                member.getPassword(),
                member.getName(),
                member.getAuthType(),
                member.getPhoneNum());
    };
    public List<MemberResponse> toMemberResponseList(List<Member> memberList){

        return memberList.stream()
                .map(member -> new MemberResponse(member.getMemberId(),
                        member.getPassword(),
                        member.getName(),
                        member.getAuthType(),
                        member.getPhoneNum()))
                .toList();

    };
}