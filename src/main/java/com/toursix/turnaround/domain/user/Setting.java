package com.toursix.turnaround.domain.user;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
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
    private Long userId;

    @Column(nullable = false)
    private boolean agreeBenefitAndEvent;

    @Column(nullable = false)
    private boolean agreePushNotification;

    @Column(nullable = false)
    private boolean agreeJoinEvent;
}
