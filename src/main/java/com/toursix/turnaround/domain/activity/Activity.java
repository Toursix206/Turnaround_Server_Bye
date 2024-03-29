package com.toursix.turnaround.domain.activity;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import com.toursix.turnaround.domain.kit.Kit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private ActivityCategory category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityPaymentStatus paymentStatus;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String detail;

    @Column(nullable = false, length = 300)
    private String imageUrl;

    @Column(nullable = false)
    private int cleanScore;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private int timeLimit;

    @Column(nullable = false)
    private int dailyParticipantsCnt;

    @OneToMany(targetEntity = Kit.class)
    private final List<Kit> kits = new ArrayList<>();

    @OneToMany(targetEntity = Kit.class)
    private final List<Kit> adKits = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "activity_guide_id", nullable = false)
    private final List<ActivityGuide> guides = new ArrayList<>();

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ActivityReview> reviews = new ArrayList<>();
}
