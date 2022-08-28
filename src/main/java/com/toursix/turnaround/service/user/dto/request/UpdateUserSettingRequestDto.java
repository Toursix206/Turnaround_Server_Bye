package com.toursix.turnaround.service.user.dto.request;

import com.toursix.turnaround.common.exception.ErrorCode;
import com.toursix.turnaround.common.exception.ValidationException;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdateUserSettingRequestDto {

    @ApiModelProperty(value = "혜택 및 이벤트 정보 수신 동의", example = "true")
    private Boolean agreeBenefitAndEvent;

    @ApiModelProperty(value = "활동 푸시 알림 수신 동의", example = "false")
    private Boolean agreePushNotification;

    @ApiModelProperty(value = "이벤트 참여 정보 제공 동의", example = "false")
    private Boolean agreeJoinEvent;

    @ApiModelProperty(hidden = true)
    public boolean isEmpty() {
        if (this.getAgreeBenefitAndEvent() == null && this.getAgreePushNotification() == null && this.getAgreeJoinEvent() == null) {
            throw new ValidationException("요청 값이 누락되었습니다.", ErrorCode.VALIDATION_EXCEPTION);
        }
        return true;
    }
}
