package com.toursix.turnaround.service.user.dto.request;

import com.toursix.turnaround.domain.user.CleanAbilityType;
import com.toursix.turnaround.domain.user.GenderType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetOnboardingInfoRequestDto {

    @ApiModelProperty(value = "이름", example = "공혁준")
    @NotBlank(message = "{onboarding.name.notBlank}")
    private String name;

    @ApiModelProperty(value = "핸드폰 번호", example = "010-1234-5678")
    @NotBlank(message = "{onboarding.phoneNumber.notBlank}")
    @Pattern(regexp = "^[0-9]{3}-([0-9]{3}|[0-9]{4})-[0-9]{4}$", message = "{onboarding.phoneNumber.pattern}")
    private String phoneNumber;

    @ApiModelProperty(value = "성별 - MAN, WOMAN", example = "MAN")
    @NotNull(message = "{onboarding.gender.notNull}")
    private GenderType gender;

    @ApiModelProperty(value = "부지런한 정도 - GOOD, NORMAL, BAD", example = "GOOD")
    @NotNull(message = "{onboarding.cleanAbility.notNull}")
    private CleanAbilityType cleanAbility;

    @ApiModelProperty(value = "주소", example = "경기 성남시 분당구 판교역로 235")
    @NotBlank(message = "{onboarding.address.notBlank}")
    private String address;

    @ApiModelProperty(value = "상세주소", example = "111동 222호")
    @NotBlank(message = "{onboarding.detailAddress.notBlank}")
    private String detailAddress;

    @ApiModelProperty(value = "공동현관 비밀번호", example = "종 1234")
    @NotBlank(message = "{onboarding.gatePassword.notBlank}")
    private String gatePassword;
}
