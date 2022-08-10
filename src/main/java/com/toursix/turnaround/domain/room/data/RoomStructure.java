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
public class RoomStructure {

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus square;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus rectangle;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoomDataStatus duplex;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomStructure(RoomDataStatus square, RoomDataStatus rectangle, RoomDataStatus duplex) {
        this.square = square;
        this.rectangle = rectangle;
        this.duplex = duplex;
    }

    public static RoomStructure of(RoomDataStatus square, RoomDataStatus rectangle, RoomDataStatus duplex) {
        return RoomStructure.builder()
                .square(square)
                .rectangle(rectangle)
                .duplex(duplex)
                .build();
    }
}
