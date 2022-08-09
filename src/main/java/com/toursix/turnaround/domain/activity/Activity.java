package com.toursix.turnaround.domain.activity;

import com.toursix.turnaround.domain.activityreview.ActivityReview;
import com.toursix.turnaround.domain.common.AuditingTimeEntity;
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

    @Column(nullable = true)
    private boolean isFree;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String detail;

    @Column(nullable = false, length = 300)
    private String imageUrl;

    @Column(nullable = false)
    private int cleanScore;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private int timeLimit;

    @Embedded
    private PayInfo payInfo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "activity_guide_id", nullable = false)
    private final List<ActivityGuide> guides = new ArrayList<>();

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ActivityReview> reviews = new ArrayList<>();
}
