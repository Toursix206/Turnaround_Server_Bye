package com.toursix.turnaround.service.user.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class OnboardingInfoCheckResponse {

    private boolean isChecked;

    @JsonProperty("isChecked")
    public boolean isChecked() {
        return isChecked;
    }

    public static OnboardingInfoCheckResponse of(boolean isChecked) {
        return OnboardingInfoCheckResponse.builder()
                .isChecked(isChecked)
                .build();
    }
}
