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
public class RoomSleep {

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus bed;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus bedding;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomSleep(RoomDataStatus bed, RoomDataStatus bedding) {
        this.bed = bed;
        this.bedding = bedding;
    }

    public static RoomSleep of(RoomDataStatus bed, RoomDataStatus bedding) {
        return RoomSleep.builder()
                .bed(bed)
                .bedding(bedding)
                .build();
    }
}
