package com.toursix.turnaround.domain.room.data;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
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
public class RoomData extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "roomData", fetch = FetchType.LAZY)
    private final List<RoomPrivateImage> roomPrivateImages = new ArrayList<>();

    @Embedded
    private RoomStructure roomStructure;

    @Embedded
    private RoomSpaceSeperation roomSpaceSeperation;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomData(RoomStructure roomStructure, RoomSpaceSeperation roomSpaceSeperation) {
        this.roomStructure = roomStructure;
        this.roomSpaceSeperation = roomSpaceSeperation;
    }

    public static RoomData of(RoomStructure roomStructure, RoomSpaceSeperation roomSpaceSeperation) {
        return RoomData.builder()
                .roomStructure(roomStructure)
                .roomSpaceSeperation(roomSpaceSeperation)
                .build();
    }
}
