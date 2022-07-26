package com.toursix.turnaround.domain.room;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import com.toursix.turnaround.domain.room.data.RoomData;
import lombok.AccessLevel;
import lombok.Builder;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_image_id")
    private RoomImage roomImage;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private final List<RoomData> roomDatas = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    public Room(int cleanLevel, int cleanScore) {
        this.cleanLevel = cleanLevel;
        this.cleanScore = cleanScore;
    }

    public static Room newInstance() {
        return Room.builder()
                .cleanLevel(1)
                .cleanScore(0)
                .build();
    }
}
