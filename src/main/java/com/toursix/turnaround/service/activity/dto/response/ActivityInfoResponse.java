package com.toursix.turnaround.service.activity.dto.response;

import com.toursix.turnaround.domain.activity.Activity;
import com.toursix.turnaround.domain.activity.ActivityCategory;
import com.toursix.turnaround.domain.activity.ActivityPaymentStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ActivityInfoResponse {

    private Long activityId;

    private ActivityCategory category;

    private ActivityPaymentStatus paymentStatus;

    private String name;

    private String detail;

    private String imageUrl;

    public static ActivityInfoResponse of(@NotNull Activity activity) {
        return ActivityInfoResponse.builder()
                .activityId(activity.getId())
                .category(activity.getCategory())
                .paymentStatus(activity.getPaymentStatus())
                .name(activity.getName())
                .detail(activity.getDetail())
                .imageUrl(activity.getImageUrl())
                .build();
    }
}
