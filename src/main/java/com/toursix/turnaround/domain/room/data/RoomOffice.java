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
public class RoomOffice {

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus desk;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus chair;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus computer;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomOffice(RoomDataStatus desk, RoomDataStatus chair, RoomDataStatus computer) {
        this.desk = desk;
        this.chair = chair;
        this.computer = computer;
    }

    public static RoomOffice of(RoomDataStatus desk, RoomDataStatus chair, RoomDataStatus computer) {
        return RoomOffice.builder()
                .desk(desk)
                .chair(chair)
                .computer(computer)
                .build();
    }
}
