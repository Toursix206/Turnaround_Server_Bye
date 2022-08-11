package com.toursix.turnaround.controller.user;

import com.toursix.turnaround.common.dto.ApiResponse;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.resolver.UserId;
import com.toursix.turnaround.service.user.UserService;
import com.toursix.turnaround.service.user.dto.request.SetOnboardingInfoRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(tags = "User")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation("[인증] 온보딩 페이지 - 나의 온보딩 정보를 설정합니다.")
    @Auth
    @PostMapping("/v1/user/onboarding")
    public ApiResponse<String> setOnboardingInfo(@Valid @RequestBody SetOnboardingInfoRequestDto request, @ApiIgnore @UserId Long userId) {
        userService.setOnboardingInfo(request, userId);
        return ApiResponse.SUCCESS;
    }
}
