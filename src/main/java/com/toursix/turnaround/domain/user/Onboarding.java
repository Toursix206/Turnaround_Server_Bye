package com.toursix.turnaround.domain.user;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Onboarding extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 30)
    private String userName;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private CleanLevelType cleanLevel;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String detailAddress;

    @Column(length = 100)
    private String gatePassword;

    @Column(nullable = false)
    private int activityLevel;

    @Column(nullable = false)
    private boolean isChecked;
}
