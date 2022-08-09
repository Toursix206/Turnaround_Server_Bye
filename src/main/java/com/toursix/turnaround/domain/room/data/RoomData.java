package com.toursix.turnaround.domain.room.data;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
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
}
