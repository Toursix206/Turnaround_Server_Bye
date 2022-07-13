package com.toursix.turnaround.service.user.dto.response;

import com.toursix.turnaround.domain.user.User;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserInfoResponse {

    private Long userId;

    public static UserInfoResponse of(@NotNull User user) {
        return UserInfoResponse.builder()
                .userId(user.getId())
                .build();
    }
}
