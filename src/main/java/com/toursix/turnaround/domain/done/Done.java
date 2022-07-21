package com.toursix.turnaround.domain.done;

import com.toursix.turnaround.domain.activityreview.ActivityReview;
import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Done extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long todoId;

    @Column(nullable = false, length = 300)
    private String image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_review_id")
    private ActivityReview activityReview;
}
