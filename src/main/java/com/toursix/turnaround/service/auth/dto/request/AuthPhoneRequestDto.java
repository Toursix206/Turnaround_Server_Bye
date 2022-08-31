package com.toursix.turnaround.service.auth.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthPhoneRequestDto {

    @ApiModelProperty(value = "전화번호 - phoneNumber", example = "010-1234-5678")
    @NotBlank(message = "{auth.phoneNumber.notBlank}")
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "{auth.phoneNumber.pattern}")
    private String phoneNumber;
}
