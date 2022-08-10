package com.toursix.turnaround.domain.room.data;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class RoomSpaceSeperation {

    @Embedded
    private RoomSleep roomSleep;

    @Embedded
    private RoomLaundry roomLaundry;

    @Embedded
    private RoomOffice roomOffice;

    @Embedded
    private RoomElseSpace roomElseSpace;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomSpaceSeperation(RoomSleep roomSleep, RoomLaundry roomLaundry, RoomOffice roomOffice, RoomElseSpace roomElseSpace) {
        this.roomSleep = roomSleep;
        this.roomLaundry = roomLaundry;
        this.roomOffice = roomOffice;
        this.roomElseSpace = roomElseSpace;
    }

    public static RoomSpaceSeperation of(RoomSleep roomSleep, RoomLaundry roomLaundry, RoomOffice roomOffice, RoomElseSpace roomElseSpace) {
        return RoomSpaceSeperation.builder()
                .roomSleep(roomSleep)
                .roomLaundry(roomLaundry)
                .roomOffice(roomOffice)
                .roomElseSpace(roomElseSpace)
                .build();
    }
}
