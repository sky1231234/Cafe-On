package com.example.order.member.controller;

import com.example.order.member.domain.Member;
import com.example.order.member.dto.request.SignUpRequest;
import com.example.order.member.dto.request.UpdateMemberRequest;
import com.example.order.member.dto.response.MemberResponse;
import com.example.order.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@Tag( name = "Member", description = "회원 API")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    @Operation(summary = "회원 정보 조회", description = "특정 회원의 정보를 조회한다.",
            responses = {
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원 정보 조회 실패")
    })
    public ResponseEntity<MemberResponse> getMemberInfo(@PathVariable("id") long id){

        return ResponseEntity.ok()
                .body(memberService.getMember(id));
    }

    @GetMapping("/all")
    @Operation(summary = "회원 정보 전체 조회", description = "모든 회원의 정보를 조회한다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "회원 정보 전체 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원 정보 전체 조회 실패")
    })
    public ResponseEntity<List<MemberResponse>> getMemberInfoList(){

        return ResponseEntity.ok()
                .body(memberService.getMemberList());
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 회원의 정보를 등록한다.",
    responses = {
            @ApiResponse(responseCode = "201", description = "회원 정보 등록 성공"),
            @ApiResponse(responseCode = "404", description = "회원 정보 등록 실패")
    })
    public ResponseEntity<Object> signup(@Validated @RequestBody SignUpRequest signUpRequest){

        memberService.signUp(signUpRequest.getMemberId(),
                signUpRequest.getPassword(),
                signUpRequest.getName(),
                signUpRequest.getAuthType(),
                signUpRequest.getPhoneNum(),
                signUpRequest.getGrade());

        return ResponseEntity.created(URI.create("/members/signup")).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "회원 정보 수정", description = "특정 회원의 정보를 수정한다.",
        responses = {
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원 정보 조회 실패")
        })
    public ResponseEntity<Object> updateMemberInfo(@PathVariable("id") long id, @Validated @RequestBody UpdateMemberRequest updateMemberRequest){

        memberService.updateMember(id,
                updateMemberRequest.getPassword(),
                updateMemberRequest.getAuthType(),
                updateMemberRequest.getPhoneNum());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "회원 탈퇴", description = "회원의 정보를 삭제한다.",
        responses = {
            @ApiResponse(responseCode = "204", description = "회원 정보 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "회원 정보 삭제 실패")
    })
    public ResponseEntity<Object> deleteMember(@PathVariable("id") long id){

        memberService.deleteMember(id);

        return ResponseEntity.noContent().build();
    }



}
