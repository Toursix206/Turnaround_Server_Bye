package com.toursix.turnaround.service.activity.dto.response;

import com.toursix.turnaround.domain.activity.Activity;
import com.toursix.turnaround.domain.activity.ActivityCategory;
import com.toursix.turnaround.domain.activity.ActivityPaymentStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityDetailInfoResponse {

    private Long activityId;

    private ActivityCategory category;

    private ActivityPaymentStatus paymentStatus;

    private String name;

    private int cleanScore;

    private int price;

    private int dailyParticipantsCnt;

    private int point;

    private int timeLimit;

    private List<ActivityGuideInfoResponse> activityGuides = new ArrayList<>();

    private List<KitInfoResponse> kits = new ArrayList<>();

    private String imageUrl;

    @Builder(access = AccessLevel.PRIVATE)
    private ActivityDetailInfoResponse(Long activityId, ActivityCategory category, ActivityPaymentStatus paymentStatus, String name,
                                       int cleanScore, int price, int dailyParticipantsCnt, int point, int timeLimit, String imageUrl) {
        this.activityId = activityId;
        this.category = category;
        this.paymentStatus = paymentStatus;
        this.name = name;
        this.cleanScore = cleanScore;
        this.price = price;
        this.dailyParticipantsCnt = dailyParticipantsCnt;
        this.point = point;
        this.timeLimit = timeLimit;
        this.imageUrl = imageUrl;
    }

    //TODO - 결제 관련 업데이트 할 때 키트 정보 추가
    public static ActivityDetailInfoResponse of(@NotNull Activity activity) {
        ActivityDetailInfoResponse response = ActivityDetailInfoResponse.builder()
                .activityId(activity.getId())
                .category(activity.getCategory())
                .paymentStatus(activity.getPaymentStatus())
                .name(activity.getName())
                .cleanScore(activity.getCleanScore())
                .price(activity.getPrice())
                .dailyParticipantsCnt(activity.getDailyParticipantsCnt())
                .point(activity.getPoint())
                .timeLimit(activity.getTimeLimit())
                .imageUrl(activity.getImageUrl())
                .build();
        response.activityGuides.addAll(
                activity.getGuides().stream()
                        .map(ActivityGuideInfoResponse::of)
                        .sorted(Comparator.comparingInt(ActivityGuideInfoResponse::getStep))
                        .collect(Collectors.toList())
        );
        return response;
    }
}
