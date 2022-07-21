package com.toursix.turnaround.domain.user;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import com.toursix.turnaround.domain.room.Room;
import com.toursix.turnaround.domain.todo.Todo;
import lombok.AccessLevel;
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

    @Column(nullable = false)
    private Long userId;

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

    @Column(nullable = false)
    private int activityLevel;

    @Column(nullable = false)
    private boolean isChecked;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "onboarding", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Todo> todos = new ArrayList<>();
}
