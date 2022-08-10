package com.toursix.turnaround.domain.room.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toursix.turnaround.domain.room.Room;
import lombok.RequiredArgsConstructor;

import static com.toursix.turnaround.domain.room.QRoom.room;

@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Room findRoomById(Long id) {
        return queryFactory
                .selectFrom(room)
                .where(room.id.eq(id))
                .fetchOne();
    }
}
