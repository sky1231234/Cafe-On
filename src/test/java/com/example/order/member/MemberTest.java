package com.example.order.member;

import com.example.order.member.domain.Member;
import com.example.order.member.errorMsg.MemberErrorMsg;
import com.example.order.member.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Member 도메인 단위 테스트")
public class MemberTest {

    @DisplayName("생성 : 정상 테스트")
    @Test
    public void create(){

        Member 회원 = MemberFixture.회원_기본생성();

        assertThat(회원.getMemberId()).isEqualTo(MemberFixture.아이디);
    }


    @DisplayName("생성 예외 : 아이디 자릿수 - 4-10자리 사이")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "abcdefghijk"})
    public void create_error_id_length(String 아이디_자릿수){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                        Member.of(아이디_자릿수,
                                MemberFixture.비밀번호,
                                MemberFixture.이름,
                                MemberFixture.권한,
                                MemberFixture.연락처,
                                MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_ID_LENGTH_ERROR_MESSAGE.getValue());

    }

    @DisplayName("생성 예외 : 아이디 유효문자 - 영문+숫자")
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "1234","한글아이디","한글12","ad한글"})
    public void create_error_id_regex(String 아이디_유효문자){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    Member.of(아이디_유효문자,
                            MemberFixture.비밀번호,
                            MemberFixture.이름,
                            MemberFixture.권한,
                            MemberFixture.연락처,
                            MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_ID_REGEX_ERROR_MESSAGE.getValue());

    }

    @DisplayName("생성 예외 : 아이디 자릿수 + 유효문자 - 영문+숫자 4-10자리 사이")
    @ParameterizedTest
    @ValueSource(strings = {"한글1", "a한글","한글123456789","abcdefghi한글"})
    public void create_error_id(String 아이디_자릿수_유효문자){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    Member.of(아이디_자릿수_유효문자,
                            MemberFixture.비밀번호,
                            MemberFixture.이름,
                            MemberFixture.권한,
                            MemberFixture.연락처,
                                MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_ID_LENGTH_ERROR_MESSAGE.getValue());

    }

    @DisplayName("생성 예외 : 비밀번호 자릿수 - 8-15자 사이")
    @ParameterizedTest
    @ValueSource(strings = {"passwor", "passwordpassword"})
    public void create_error_password(String 비밀번호_자릿수){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    Member.of(MemberFixture.아이디,
                            비밀번호_자릿수,
                            MemberFixture.이름,
                            MemberFixture.권한,
                            MemberFixture.연락처,
                            MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_PASSWORD_LENGTH_ERROR_MESSAGE.getValue());

    }

    @DisplayName("생성 예외 : 이름 자릿수 - 1-5자 사이")
    @ParameterizedTest
    @ValueSource(strings = {"", "김박최이정장"})
    public void create_error_name(String 이름_자릿수){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    Member.of(MemberFixture.아이디,
                            MemberFixture.비밀번호,
                            이름_자릿수,
                            MemberFixture.권한,
                            MemberFixture.연락처,
                            MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_NAME_LENGTH_ERROR_MESSAGE.getValue());

    }

    @DisplayName("생성 예외 : 휴대폰번호 자릿수 - 9-11자리 사이")
    @ParameterizedTest
    @ValueSource(strings = {"01011111", "010111111111"})
    public void create_error_phoneNum_length(String 연락처_자릿수){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    Member.of(MemberFixture.아이디,
                            MemberFixture.비밀번호,
                            MemberFixture.이름,
                            MemberFixture.권한,
                            연락처_자릿수,
                            MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_PHONE_NUM_LENGTH_ERROR_MESSAGE.getValue());
    }

    @DisplayName("생성 예외 : 휴대폰번호 유효문자 - 숫자")
    @ParameterizedTest
    @ValueSource(strings = {"연락처열한자리한글일때", "aaaaaaaaaaa"})
    public void create_error_phoneNum_only_number_regex(String 연락처_유효문자){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    Member.of(MemberFixture.아이디,
                            MemberFixture.비밀번호,
                            MemberFixture.이름,
                            MemberFixture.권한,
                            연락처_유효문자,
                            MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_PHONE_NUM_ONLY_NUMBER_REGEX_ERROR_MESSAGE.getValue());

    }

    @DisplayName("생성 예외 : 휴대폰번호 자릿수 + 유효문자 - 9-11자리 숫자")
    @ParameterizedTest
    @ValueSource(strings = {"가나다라마바사아", "가나다라마바사아자차카타"})
    public void create_error_phoneNum(String 예외_연락처){

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    Member.of(MemberFixture.아이디,
                            MemberFixture.비밀번호,
                            MemberFixture.이름,
                            MemberFixture.권한,
                            예외_연락처,
                            MemberFixture.등급);
                })
                .withMessage(MemberErrorMsg.MEMBER_PHONE_NUM_LENGTH_ERROR_MESSAGE.getValue());

    }

}
