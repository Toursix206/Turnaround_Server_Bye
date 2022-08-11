package com.toursix.turnaround.service.activity.dto.response;

import com.toursix.turnaround.domain.activity.ActivityGuide;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ActivityGuideInfoResponse {

    private int step;

    private String content;

    private String imageUrl;

    public static ActivityGuideInfoResponse of(ActivityGuide activityGuide) {
        return ActivityGuideInfoResponse.builder()
                .step(activityGuide.getStep())
                .content(activityGuide.getContent())
                .imageUrl(activityGuide.getImageUrl())
                .build();
    }
}
