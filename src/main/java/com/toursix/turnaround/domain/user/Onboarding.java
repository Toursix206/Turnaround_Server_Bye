package com.toursix.turnaround.domain.user;

import com.toursix.turnaround.domain.activity.ActivityReview;
import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import com.toursix.turnaround.domain.room.Room;
import com.toursix.turnaround.domain.todo.Todo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Onboarding extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private CleanAbilityType cleanAbility;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String detailAddress;

    @Column(length = 100)
    private String gatePassword;

    @Column(nullable = false, length = 300)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isChecked;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @OneToMany(mappedBy = "onboarding", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "onboarding", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ActivityReview> activityReviews = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    public Onboarding(String name, String email, String phoneNumber, GenderType gender, CleanAbilityType cleanAbility,
                      String address, String detailAddress, String gatePassword, String imageUrl, boolean isChecked, Room room) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.cleanAbility = cleanAbility;
        this.address = address;
        this.detailAddress = detailAddress;
        this.gatePassword = gatePassword;
        this.imageUrl = imageUrl;
        this.isChecked = isChecked;
        this.room = room;
    }

    public static Onboarding newInstance(String name, String email, String phoneNumber, Room room) {
        return Onboarding.builder()
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .imageUrl("TODO - 기본 이미지 파일 경로로 수정")
                .isChecked(false)
                .room(room)
                .build();
    }

    public void updateInfo(GenderType gender, CleanAbilityType cleanAbility, String address, String detailAddress, String gatePassword) {
        this.gender = gender;
        this.cleanAbility = cleanAbility;
        this.address = address;
        this.detailAddress = detailAddress;
        this.gatePassword = gatePassword;
        this.isChecked = true;
    }
}
