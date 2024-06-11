package com.example.order.member.service;

import com.example.order.member.domain.AuthType;
import com.example.order.member.domain.Grade;
import com.example.order.member.domain.Member;
import com.example.order.member.dto.response.MemberResponse;
import com.example.order.member.errorCode.MemberErrorCode;
import com.example.order.member.mapper.MemberMapper;
import com.example.order.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberResponse getMember(long id){

        Member member = checkExistMember(id);

        return memberMapper.toMemberResponse(member);
    }

    public List<MemberResponse> getMemberList(){
        List<Member> memberList = memberRepository.findAll();

        return memberMapper.toMemberResponseList(memberList);
    }

    @Transactional
    public Member signUp(String memberId, String password, String name, AuthType authType, String phoneNum, Grade grade){

        Optional<Member> member = memberRepository.findByMemberId(memberId);

        if(member.isPresent()){
            throw new RuntimeException(MemberErrorCode.ALREADY_EXIST_MEMBER_ID_EXCEPTION.getValue());
        }

        Member newMember = Member.of(memberId, password, name, authType, phoneNum, grade);
        return memberRepository.save(newMember);
    }

    @Transactional
    public void updateMember(long id, String password, AuthType authType, String phoneNum){

        Member member = checkExistMember(id);
        member.updateMember(password, authType, phoneNum);
    }

    @Transactional
    public void deleteMember(long id){
        memberRepository.delete(checkExistMember(id));
    }

    private Member checkExistMember(long id){

        return memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
