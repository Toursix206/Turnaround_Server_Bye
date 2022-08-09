package com.toursix.turnaround.domain.done;

import com.toursix.turnaround.domain.activityreview.ActivityReview;
import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import com.toursix.turnaround.domain.todo.Todo;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    @Column(nullable = false, length = 300)
    private String imageUrl;

    @OneToOne(mappedBy = "done", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private ActivityReview activityReview;
}
