package com.toursix.turnaround.controller.user;

import com.toursix.turnaround.common.dto.ApiResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.resolver.UserId;
import com.toursix.turnaround.service.user.UserRetrieveService;
import com.toursix.turnaround.service.user.dto.response.CheckOnboardingInfoResponse;
import com.toursix.turnaround.service.user.dto.response.UserMyPageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "User")
@RequiredArgsConstructor
@RestController
public class UserRetrieveController {

    private final UserRetrieveService userRetrieveService;

    @ApiOperation("[인증] 온보딩 페이지 - 나의 온보딩 정보 등록여부를 확인합니다.")
    @Auth
    @GetMapping("/v1/user/onboarding/check")
    public ApiResponse<CheckOnboardingInfoResponse> checkMyOnboardingInfo(@ApiIgnore @UserId Long userId) {
        return ApiResponse.success(SuccessCode.CHECK_ONBOARDING_SUCCESS, userRetrieveService.checkMyOnboardingInfo(userId));
    }

    @ApiOperation("[인증] 마이 페이지 - 나의 정보를 확인합니다.")
    @Auth
    @GetMapping("/v1/user")
    public ApiResponse<UserMyPageResponse> getUserMyPageInfo(@ApiIgnore @UserId Long userId) {
        return ApiResponse.success(SuccessCode.READ_MYPAGE_SUCCESS, userRetrieveService.getUserMyPageInfo(userId));
    }
}
