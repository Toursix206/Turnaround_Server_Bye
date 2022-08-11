package com.toursix.turnaround.service.user.dto.response;

import com.toursix.turnaround.domain.user.User;
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

    public static UserMyPageResponse of(User user) {
        return UserMyPageResponse.builder()
                .name(user.getOnboarding().getName())
                .level(user.getOnboarding().getRoom().getCleanLevel())
                .email(user.getOnboarding().getEmail())
                .point(user.getPoint().getAmount())
                .build();
    }
}
