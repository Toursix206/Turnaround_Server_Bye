package com.toursix.turnaround.domain.room.data;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class RoomElseSpace {

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus kitchen;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus restroom;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus glassWindow;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomElseSpace(RoomDataStatus kitchen, RoomDataStatus restroom, RoomDataStatus glassWindow) {
        this.kitchen = kitchen;
        this.restroom = restroom;
        this.glassWindow = glassWindow;
    }

    public static RoomElseSpace of(RoomDataStatus kitchen, RoomDataStatus restroom, RoomDataStatus glassWindow) {
        return RoomElseSpace.builder()
                .kitchen(kitchen)
                .restroom(restroom)
                .glassWindow(glassWindow).build();
    }
}
