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
public class RoomLaundry {

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus washer;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus washHanger;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomLaundry(RoomDataStatus washer, RoomDataStatus washHanger) {
        this.washer = washer;
        this.washHanger = washHanger;
    }

    public static RoomLaundry of(RoomDataStatus washer, RoomDataStatus washHanger) {
        return RoomLaundry.builder()
                .washer(washer)
                .washHanger(washHanger)
                .build();
    }
}
