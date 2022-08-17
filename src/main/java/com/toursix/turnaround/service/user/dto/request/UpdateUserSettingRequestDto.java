package com.toursix.turnaround.service.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserSettingRequestDto {

    @ApiModelProperty(value = "혜택 및 이벤트 정보 수신 동의", example = "true")
    private boolean agreeBenefitAndEvent;

    @ApiModelProperty(value = "활동 푸시 알림 수신 동의", example = "false")
    private boolean agreePushNotification;

    @ApiModelProperty(value = "이벤트 참여 정보 제공 동의", example = "false")
    private boolean agreeJoinEvent;
}
