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
public class RoomSleep {

    @Column(nullable = false)
    private boolean bed;

    @Column(nullable = false)
    private boolean bedding;
}
