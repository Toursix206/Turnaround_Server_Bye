package com.toursix.turnaround.service.activity;

import com.toursix.turnaround.domain.activity.repository.ActivityRepository;
import com.toursix.turnaround.service.activity.dto.request.RetrieveActivitiesRequestDto;
import com.toursix.turnaround.service.activity.dto.response.ActivityDetailInfoResponse;
import com.toursix.turnaround.service.activity.dto.response.ActivityPagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ActivityRetrieveService {

    private final ActivityRepository activityRepository;

    public ActivityPagingResponse retrieveActivitiesUsingPaging(RetrieveActivitiesRequestDto request, Pageable pageable) {
        return ActivityPagingResponse.of(activityRepository.findActivitiesByFilterConditionUsingPaging(request.getPaymentStatus(), request.getCategory(), pageable));
    }

    //TODO - 결제 관련 업데이트 할 때 키트 정보 추가
    public ActivityDetailInfoResponse retrieveActivityById(Long activityId) {
        return ActivityDetailInfoResponse.of(ActivityServiceUtils.findActivityById(activityRepository, activityId));
    }
}
