package com.toursix.turnaround.service.user.dto.response;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class OnboardingInfoCheckResponse {

    private boolean isAlreadySet;

    public static OnboardingInfoCheckResponse of(boolean isAlreadySet) {
        return OnboardingInfoCheckResponse.builder()
                .isAlreadySet(isAlreadySet)
                .build();
    }
}
