package com.toursix.turnaround.service.user.dto.response;

import com.toursix.turnaround.domain.user.Setting;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserSettingResponse {

    private boolean agreeBenefitAndEvent;

    private boolean agreePushNotification;

    private boolean agreeJoinEvent;

    public static UserSettingResponse of(Setting setting) {
        return UserSettingResponse.builder()
                .agreeBenefitAndEvent(setting.isAgreeBenefitAndEvent())
                .agreePushNotification(setting.isAgreePushNotification())
                .agreeJoinEvent(setting.isAgreeJoinEvent())
                .build();
    }
}
