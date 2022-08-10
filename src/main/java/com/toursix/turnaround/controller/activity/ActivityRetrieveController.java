package com.toursix.turnaround.controller.activity;

import com.toursix.turnaround.common.dto.ApiResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.validator.AllowedSortProperties;
import com.toursix.turnaround.service.activity.ActivityRetrieveService;
import com.toursix.turnaround.service.activity.dto.request.RetrieveActivitiesRequestDto;
import com.toursix.turnaround.service.activity.dto.response.ActivityPagingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Activity")
@Validated
@RequiredArgsConstructor
@RestController
public class ActivityRetrieveController {

    private final ActivityRetrieveService activityRetrieveService;

    @ApiOperation("[인증] 진열 페이지 - 활동들을 카테고리, 정렬 조건에 맞게 스크롤 페이지네이션으로 조회합니다.")
    @Auth
    @GetMapping("/v1/activities")
    public ApiResponse<ActivityPagingResponse> retrieveActivitiesUsingPaging(@Valid RetrieveActivitiesRequestDto request,
                                                                             @AllowedSortProperties({"createdAt", "dailyParticipantsCnt"}) Pageable pageable) {
        return ApiResponse.success(SuccessCode.READ_ACTIVITIES_SUCCESS, activityRetrieveService.retrieveActivitiesUsingPaging(request, pageable));
    }
}
