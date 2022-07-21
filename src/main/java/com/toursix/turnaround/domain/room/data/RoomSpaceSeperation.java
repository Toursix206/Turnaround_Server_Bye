package com.toursix.turnaround.domain.room.data;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
