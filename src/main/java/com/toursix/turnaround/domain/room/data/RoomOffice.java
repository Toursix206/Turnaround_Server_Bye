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
public class RoomOffice {

    @Column(nullable = false)
    private boolean desk;

    @Column(nullable = false)
    private boolean chair;

    @Column(nullable = false)
    private boolean computer;
}
