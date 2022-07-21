package com.toursix.turnaround.domain.room;

import com.toursix.turnaround.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private int cleanLevel;

    @Column(nullable = true)
    private int cleanScore;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_image_id")
    private RoomImage roomImage;
}
