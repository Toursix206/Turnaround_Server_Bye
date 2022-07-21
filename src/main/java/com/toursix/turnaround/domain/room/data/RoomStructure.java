package com.toursix.turnaround.domain.room.data;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class RoomStructure {

    @Column(nullable = false)
    private boolean square;

    @Column(nullable = false)
    private boolean rectangle;

    @Column(nullable = false)
    private boolean duplex;
}
