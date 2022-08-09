package com.toursix.turnaround.domain.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
    private LocalDateTime turnaroundAt;

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
