package com.toursix.turnaround.service.activity.dto.response;

import com.toursix.turnaround.domain.activity.ActivityReview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityReviewsPagingResponse {

    private static final long LAST_PAGE = -1L;

    private List<ActivityReviewInfoResponse> contents = new ArrayList<>();
    private long lastPage;
    private long nextPage;

    private ActivityReviewsPagingResponse(List<ActivityReviewInfoResponse> contents, long lastPage, long nextPage) {
        this.contents = contents;
        this.lastPage = lastPage;
        this.nextPage = nextPage;
    }

    public static ActivityReviewsPagingResponse of(Page<ActivityReview> activityReviewPaging) {
        if (!activityReviewPaging.hasNext()) {
            return ActivityReviewsPagingResponse.newLastScroll(activityReviewPaging.getContent(), activityReviewPaging.getTotalPages() - 1);
        }
        return ActivityReviewsPagingResponse.newPagingHasNext(activityReviewPaging.getContent(), activityReviewPaging.getTotalPages() - 1, activityReviewPaging.getPageable().getPageNumber() + 1);
    }

    private static ActivityReviewsPagingResponse newLastScroll(List<ActivityReview> activityReviewPaging, long lastPage) {
        return newPagingHasNext(activityReviewPaging, lastPage, LAST_PAGE);
    }

    private static ActivityReviewsPagingResponse newPagingHasNext(List<ActivityReview> activityReviewPaging, long lastPage, long nextPage) {
        return new ActivityReviewsPagingResponse(getContents(activityReviewPaging), lastPage, nextPage);
    }

    private static List<ActivityReviewInfoResponse> getContents(List<ActivityReview> activityReviewPaging) {
        return activityReviewPaging.stream()
                .map(ActivityReviewInfoResponse::of)
                .collect(Collectors.toList());
    }
}
