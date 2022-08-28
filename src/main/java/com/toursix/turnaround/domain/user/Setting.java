package com.toursix.turnaround.domain.user;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import com.toursix.turnaround.service.user.dto.request.UpdateUserSettingRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Setting extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean agreeBenefitAndEvent;

    @Column(nullable = false)
    private boolean agreePushNotification;

    @Column(nullable = false)
    private boolean agreeJoinEvent;

    @Builder(access = AccessLevel.PACKAGE)
    public Setting(boolean agreeBenefitAndEvent, boolean agreePushNotification, boolean agreeJoinEvent) {
        this.agreeBenefitAndEvent = agreeBenefitAndEvent;
        this.agreePushNotification = agreePushNotification;
        this.agreeJoinEvent = agreeJoinEvent;
    }

    public static Setting newInstance() {
        return Setting.builder()
                .agreeBenefitAndEvent(true)
                .agreePushNotification(true)
                .agreeJoinEvent(false)
                .build();
    }

    public void updateInfo(UpdateUserSettingRequestDto request) {
        if (request.getAgreeBenefitAndEvent() != null) {
            this.agreeBenefitAndEvent = request.getAgreeBenefitAndEvent();
        }
        if (request.getAgreePushNotification() != null) {
            this.agreePushNotification = request.getAgreePushNotification();
        }
        if (request.getAgreeJoinEvent() != null) {
            this.agreeJoinEvent = request.getAgreeJoinEvent();
        }
    }
}
