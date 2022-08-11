package com.toursix.turnaround.controller.activity;

import com.toursix.turnaround.common.dto.ErrorResponse;
import com.toursix.turnaround.common.dto.SuccessResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.validator.AllowedSortProperties;
import com.toursix.turnaround.service.activity.ActivityRetrieveService;
import com.toursix.turnaround.service.activity.dto.request.RetrieveActivitiesRequestDto;
import com.toursix.turnaround.service.activity.dto.response.ActivityDetailInfoResponse;
import com.toursix.turnaround.service.activity.dto.response.ActivityPagingResponse;
import com.toursix.turnaround.service.activity.dto.response.ActivityReviewsPagingResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Activity")
@Validated
@RequiredArgsConstructor
@RestController
public class ActivityRetrieveController {

    private final ActivityRetrieveService activityRetrieveService;

    @ApiOperation(
            value = "[인증] 진열 페이지 - 활동들을 카테고리, 정렬 조건에 맞게 스크롤 페이지네이션으로 조회합니다.",
            notes = "paymentStatus - FREE (무료), PAID (유료)\n" +
                    "category - null (전체), TABLE (책상), WASHER (세탁기), RESTROOM (화장실), KITCHEN (주방), BED (침대), WINDOW (창문), ETC (기타)\n" +
                    "page - 0 부터 조회를 시작합니다.\n" +
                    "size - 1 ~ 100 사이의 값으로 한 페이지의 크기를 설정합니다.\n" +
                    "sort - 정렬 조건을 설정합니다. createdAt,DESC (최신순), dailyParticipantsCnt,DESC (인기순)\n" +
                    "response 의 nextPage 를 다음 요청의 page 에 담아서 다음 스크롤에 대한 응답을 받습니다.\n" +
                    "nextPage 가 -1 인 경우 더 이상 불러올 정보가 없다는 뜻입니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "활동 리스트 조회 성공입니다."),
            @ApiResponse(code = 400, message = "허용하지 않는 정렬기준을 입력했습니다.\n", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/activities")
    public SuccessResponse<ActivityPagingResponse> retrieveActivitiesUsingPaging(@Valid RetrieveActivitiesRequestDto request,
                                                                                 @AllowedSortProperties({"createdAt", "dailyParticipantsCnt"}) Pageable pageable) {
        return SuccessResponse.success(SuccessCode.READ_ACTIVITIES_SUCCESS, activityRetrieveService.retrieveActivitiesUsingPaging(request, pageable));
    }

    //TODO - 결제 관련 업데이트 할 때 키트 정보 추가
    @ApiOperation(
            value = "[인증] 상세 페이지 - 특정 활동의 세부 정보를 조회합니다.",
            notes = "결제 관련 업데이트가 되면 유료 활동에 대해 키트 정보가 포함될 예정입니다.\n" +
                    "현재는 무료 활동만 존재하기 때문에 키트는 빈 배열입니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "활동 조회 성공입니다."),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "존재하지 않는 활동입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/activity/{activityId}")
    public SuccessResponse<ActivityDetailInfoResponse> retrieveActivityById(@ApiParam(name = "activityId", value = "조회할 activity의 id", required = true, example = "1")
                                                                            @PathVariable Long activityId) {
        return SuccessResponse.success(SuccessCode.READ_ACTIVITY_SUCCESS, activityRetrieveService.retrieveActivityById(activityId));
    }

    @ApiOperation(
            value = "[인증] 상세 페이지 - 특정 활동의 리뷰를 정렬 조건에 맞게 스크롤 페이지네이션으로 조회합니다.",
            notes = "page - 0 부터 조회를 시작합니다.\n" +
                    "size - 1 ~ 100 사이의 값으로 한 페이지의 크기를 설정합니다.\n" +
                    "sort - 정렬 조건을 설정합니다. createdAt,DESC (최신순), grade,DESC (별점순)\n" +
                    "response 의 nextPage 를 다음 요청의 page 에 담아서 다음 스크롤에 대한 응답을 받습니다.\n" +
                    "nextPage 가 -1 인 경우 더 이상 불러올 정보가 없다는 뜻입니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "활동 리뷰 리스트 조회 성공입니다."),
            @ApiResponse(code = 400, message = "허용하지 않는 정렬기준을 입력했습니다.\n", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "존재하지 않는 활동입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/activity/{activityId}/reviews")
    public SuccessResponse<ActivityReviewsPagingResponse> retrieveActivityReviewsUsingPaging(@ApiParam(name = "activityId", value = "조회할 activity의 id", required = true, example = "1")
                                                                                             @PathVariable Long activityId,
                                                                                             @AllowedSortProperties({"createdAt", "grade"}) Pageable pageable) {
        return SuccessResponse.success(SuccessCode.READ_ACTIVITY_REVIEWS_SUCCESS, activityRetrieveService.retrieveActivityReviewsUsingPaging(activityId, pageable));
    }
}
