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
public class Point extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Builder(access = AccessLevel.PACKAGE)
    public Point(int amount) {
        this.amount = amount;
    }

    public static Point newInstance() {
        return Point.builder()
                .amount(0)
                .build();
    }
}
