package com.example.order.member.domain;

import com.example.order.global.common.BaseTimeEntity;
import com.example.order.member.errorMsg.MemberErrorMsg;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(force = true)
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private final String memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private final String name;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Column(nullable = false)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Transient
    public static final String MEMBER_ID_REG = "^[a-zA-Z0-9]{4,10}$";
    @Transient
    public static final String PHONE_NUM_NUMBER_REG = "^\\d+$";

    @Transient
    private static final int MIN_ID_LENGTH = 4;
    @Transient
    private static final int MAX_ID_LENGTH = 10;
    @Transient
    private static final int MIN_PASSWORD_LENGTH = 8;
    @Transient
    private static final int MAX_PASSWORD_LENGTH = 15;
    @Transient
    private static final int MIN_NAME_LENGTH = 1;
    @Transient
    private static final int MAX_NAME_LENGTH = 5;
    @Transient
    private static final int MIN_PHONE_NUM_LENGTH = 9;
    @Transient
    private static final int MAX_PHONE_NUM_LENGTH = 11;

    private Member(String memberId, String password, String name, AuthType authType, String phoneNum, Grade grade){
        validation(memberId, password, name, phoneNum);
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.authType = authType;
        this.phoneNum = phoneNum;
        this.grade = grade;
    }

    public static Member of(String memberId, String password, String name, AuthType authType, String phoneNum, Grade grade){
        return new Member(memberId, password, name, authType, phoneNum, grade);
    }

    private Member(long id, String memberId, String password, String name, AuthType authType, String phoneNum){
        validation(memberId, password, name, phoneNum);
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.authType = authType;
        this.phoneNum = phoneNum;
    }

    public static Member of(long id, String memberId, String password, String name, AuthType authType, String phoneNum){
        return new Member(id, memberId, password, name, authType, phoneNum);
    }

    public void validation(String memberId, String password, String name, String phoneNum){
        check_id_length(memberId);
        check_id_regex(memberId);

        isPasswordNull(password);
        check_password_length(password);

        isNameNull(name);
        check_name_length(name);

        check_phone_num_length(phoneNum);
        check_phone_num_only_number_regex(phoneNum);
    }

    public void check_id_length(String memberId){

        if(memberId.length() < MIN_ID_LENGTH || memberId.length() > MAX_ID_LENGTH){
            throw new IllegalArgumentException(MemberErrorMsg.MEMBER_ID_LENGTH_ERROR_MESSAGE.getValue());
        }

    }

    public void check_id_regex(String memberId){

        if(!memberId.matches(MEMBER_ID_REG)){
            throw new IllegalArgumentException(MemberErrorMsg.MEMBER_ID_REGEX_ERROR_MESSAGE.getValue());
        }
    }

    public void check_password_length(String password){

        if(password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH){
            throw new IllegalArgumentException(MemberErrorMsg.MEMBER_PASSWORD_LENGTH_ERROR_MESSAGE.getValue());

        }
    }

    public void isPasswordNull(String password){

        if(password == null){
            throw new NullPointerException(MemberErrorMsg.MEMBER_PASSWORD_NULL_ERROR_MESSAGE.getValue());
        }
    }

    public void isNameNull(String name){

        if(name == null){
            throw new NullPointerException(MemberErrorMsg.MEMBER_NAME_NULL_ERROR_MESSAGE.getValue());
        }
    }

    public void check_name_length(String name){

        if(name.isEmpty() || name.length() > MAX_NAME_LENGTH){
            throw new IllegalArgumentException(MemberErrorMsg.MEMBER_NAME_LENGTH_ERROR_MESSAGE.getValue());
        }

    }

    public void check_phone_num_length(String phoneNum){

        if(phoneNum.length() < MIN_PHONE_NUM_LENGTH || phoneNum.length() > MAX_PHONE_NUM_LENGTH){
            throw new IllegalArgumentException(MemberErrorMsg.MEMBER_PHONE_NUM_LENGTH_ERROR_MESSAGE.getValue());
        }

    }

    public void check_phone_num_only_number_regex(String phoneNum){

        if(!phoneNum.matches(PHONE_NUM_NUMBER_REG)){
            throw new IllegalArgumentException(MemberErrorMsg.MEMBER_PHONE_NUM_ONLY_NUMBER_REGEX_ERROR_MESSAGE.getValue());

        }

    }

    public void updateMember(String password, AuthType authType, String phoneNum){
        this.password = password;
        this.authType = authType;
        this.phoneNum = phoneNum;
    }

    public void updateMemberGrade(Grade grade){
        this.grade = grade;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId) &&
                Objects.equals(password, member.password) &&
                Objects.equals(authType, member.authType) &&
                Objects.equals(phoneNum, member.phoneNum);
    }



}
