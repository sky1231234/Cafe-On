package com.example.order.cafe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Getter
public class CafeInfoUpdateRequest {

    @NotBlank(message = "이름은 필수 입력 값 입니다.")
    private final String name;
    private final String explain;
    @NotBlank(message = "연락처는 숫자만 입력할 수 있습니다.")
    @Size(min = 9, max = 11)
    @Pattern(regexp = "^\\d+$")
    private final String contactNumber;
    @NotBlank(message = "주소은 필수 입력 값 입니다.")
    private final String address;

    public CafeInfoUpdateRequest(String name, String explain, String contactNumber, String address){
        this.name = name;
        this.explain = explain;
        this.contactNumber = contactNumber;
        this.address = address;
    }

}
