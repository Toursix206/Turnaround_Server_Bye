package com.toursix.turnaround.service.activity.dto.response;

import com.toursix.turnaround.domain.activity.ActivityReview;
import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ActivityReviewInfoResponse {

    private Long activityReviewId;

    private String userImageUrl;

    private String userName;

    private int userLevel;

    private double grade;

    private String content;

    private String reviewImageUrl;

    private String createdAt;

    public static ActivityReviewInfoResponse of(@NotNull ActivityReview activityReview) {
        return ActivityReviewInfoResponse.builder()
                .activityReviewId(activityReview.getId())
                .userImageUrl(activityReview.getOnboarding().getImageUrl())
                .userName(encryptUserName(activityReview.getOnboarding().getName()))
                .userLevel(activityReview.getOnboarding().getRoom().getCleanLevel())
                .grade(activityReview.getGrade())
                .content(activityReview.getContent())
                .reviewImageUrl(activityReview.getDone().getImageUrl())
                .createdAt(activityReview.getCreatedAt().toString())
                .build();
    }

    private static String encryptUserName(String name) {
        String encryptedName = Character.toString(name.charAt(0));
        return encryptedName.concat("*".repeat(name.length() - 1));
    }
}
