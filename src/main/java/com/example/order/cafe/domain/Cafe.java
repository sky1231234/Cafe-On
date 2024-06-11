package com.example.order.cafe.domain;

import com.example.order.cafe.errorMsg.CafeErrorMsg;
import com.example.order.global.common.BaseTimeEntity;
import com.example.order.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor(force = true)
@Entity
public class Cafe extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true)
    private Member member;

    @Embedded
    @JoinColumn(unique = true)
    private CafeInfo cafeInfo;

    @Embedded
    @JoinColumn(unique = true)
    private BusinessHours businessHours;

    private Cafe(Member member, CafeInfo cafeInfo, BusinessHours businessHours){
        validation(member, cafeInfo, businessHours);
        this.member = member;
        this.cafeInfo = cafeInfo;
        this.businessHours = businessHours;
    }

    public static Cafe of(Member member, CafeInfo cafeInfo, BusinessHours businessHours){
        return new Cafe(member, cafeInfo, businessHours);
    }

    private Cafe(CafeInfo cafeInfo, BusinessHours businessHours){
        this.cafeInfo = cafeInfo;
        this.businessHours = businessHours;
    }

    public static Cafe of(CafeInfo cafeInfo, BusinessHours businessHours){
        return new Cafe(cafeInfo, businessHours);
    }

    public void validation(Member member, CafeInfo cafeInfo, BusinessHours businessHours){
        isMemberInfoNull(member);
        isCafeInfoNull(cafeInfo);
        isBusinessHoursNull(businessHours);
    }

    public void isMemberInfoNull(Member member){
        if(member == null){
            throw new IllegalArgumentException(CafeErrorMsg.MEMBER_NULL_ERROR_MESSAGE.getValue());
        }
    }

    public void isCafeInfoNull(CafeInfo cafeInfo){
        if(cafeInfo == null){
            throw new IllegalArgumentException(CafeErrorMsg.CAFE_INFO_NULL_ERROR_MESSAGE.getValue());
        }
    }

    public void isBusinessHoursNull(BusinessHours businessHours){
        if(businessHours == null){
            throw new IllegalArgumentException(CafeErrorMsg.BUSINESS_HOURS_NULL_ERROR_MESSAGE.getValue());
        }
    }

    public Member getMember(){
        return Member.of(member.getMemberId(), member.getPassword(), member.getName(), member.getAuthType(), member.getPhoneNum(), member.getGrade());
    }

    public CafeInfo getCafeInfo(){
        return CafeInfo.of(cafeInfo.getName(), cafeInfo.getExplain(), cafeInfo.getContactNumber(), cafeInfo.getAddress());
    }

    public BusinessHours getBusinessHours(){
        return BusinessHours.of(businessHours.getOperationTimeList());
    }

    public void updateCafe(CafeInfo updateCafeInfo, BusinessHours updateBusinessHours){
        this.cafeInfo = updateCafeInfo;
        this.businessHours.addBusinessHoursList(updateBusinessHours.getOperationTimePerDayList());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cafe cafe = (Cafe) o;
        return Objects.equals(member, cafe.member) &&
                Objects.equals(cafeInfo, cafe.cafeInfo) &&
                Objects.equals(businessHours, cafe.businessHours);
    }
}
