package com.toursix.turnaround.service.user.dto.response;

import com.toursix.turnaround.domain.room.Room;
import com.toursix.turnaround.domain.user.Onboarding;
import com.toursix.turnaround.domain.user.Point;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserMyPageResponse {

    private String name;

    private int level;

    private String email;

    private int point;

    public static UserMyPageResponse of(Onboarding onboarding, Room room, Point point) {
        return UserMyPageResponse.builder()
                .name(onboarding.getName())
                .level(room.getCleanLevel())
                .email(onboarding.getEmail())
                .point(point.getAmount())
                .build();
    }
}
