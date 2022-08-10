package com.toursix.turnaround.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toursix.turnaround.domain.user.Point;
import lombok.RequiredArgsConstructor;

import static com.toursix.turnaround.domain.user.QPoint.point;

@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Point findPointById(Long id) {
        return queryFactory
                .selectFrom(point)
                .where(point.id.eq(id))
                .fetchOne();
    }
}
