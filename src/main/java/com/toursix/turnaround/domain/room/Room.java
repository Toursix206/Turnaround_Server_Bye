package com.toursix.turnaround.domain.room;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import com.toursix.turnaround.domain.room.data.RoomData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cleanLevel;

    @Column(nullable = false)
    private int cleanScore;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private final List<RoomImage> roomImages = new ArrayList<>();

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private final List<RoomData> roomDatas = new ArrayList<>();
}
