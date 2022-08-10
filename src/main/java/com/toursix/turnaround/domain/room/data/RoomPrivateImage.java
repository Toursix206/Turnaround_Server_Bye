package com.toursix.turnaround.domain.room.data;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomPrivateImage extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_data_id", nullable = false)
    private RoomData roomData;

    @Column(nullable = false, length = 300)
    private String imageUrl;

    @Builder(access = AccessLevel.PACKAGE)
    public RoomPrivateImage(RoomData roomData, String imageUrl) {
        this.roomData = roomData;
        this.imageUrl = imageUrl;
    }

    public static RoomPrivateImage of(RoomData roomData, String imageUrl) {
        return RoomPrivateImage.builder()
                .roomData(roomData)
                .imageUrl(imageUrl)
                .build();
    }
}
