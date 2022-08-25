package com.toursix.turnaround.controller.user;

import com.toursix.turnaround.common.dto.ErrorResponse;
import com.toursix.turnaround.common.dto.SuccessResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.resolver.UserId;
import com.toursix.turnaround.service.user.UserRetrieveService;
import com.toursix.turnaround.service.user.dto.response.CheckOnboardingInfoResponse;
import com.toursix.turnaround.service.user.dto.response.UserMyPageResponse;
import com.toursix.turnaround.service.user.dto.response.UserSettingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "User")
@RequiredArgsConstructor
@RestController
public class UserRetrieveController {

    private final UserRetrieveService userRetrieveService;

    @ApiOperation(
            value = "[인증] 온보딩 페이지 - 나의 온보딩 정보 등록여부를 확인합니다.",
            notes = "등록이 안된 경우 온보딩 페이지로 이동해서 필요한 정보를 입력받아야 합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "온보딩 등록여부 조회 성공입니다."),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/user/onboarding/check")
    public ResponseEntity<CheckOnboardingInfoResponse> checkMyOnboardingInfo(@ApiIgnore @UserId Long userId) {
        return SuccessResponse.success(SuccessCode.CHECK_ONBOARDING_SUCCESS, userRetrieveService.checkMyOnboardingInfo(userId));
    }

    @ApiOperation(
            value = "[인증] 마이 페이지 - 나의 정보를 조회합니다.",
            notes = "나의 이름, 레벨, 이메일, 터닝포인트를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "마이페이지 조회 성공입니다."),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/user")
    public ResponseEntity<UserMyPageResponse> getUserMyPageInfo(@ApiIgnore @UserId Long userId) {
        return SuccessResponse.success(SuccessCode.READ_MYPAGE_SUCCESS, userRetrieveService.getUserMyPageInfo(userId));
    }

    @ApiOperation(
            value = "[인증] 마이 페이지 - 나의 설정 정보를 조회합니다.",
            notes = "혜택 및 이벤트 정보 수신 동의, 활동 푸시 알림 수신 동의, 이벤트 참여 정보 제공 동의를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "나의 설정 정보 조회 성공입니다."),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @GetMapping("/v1/user/setting")
    public ResponseEntity<UserSettingResponse> getUserMyPageSettingInfo(@ApiIgnore @UserId Long userId) {
        return SuccessResponse.success(SuccessCode.READ_MYPAGE_SETTINGS_SUCCESS, userRetrieveService.getUserMyPageSettingInfo(userId));
    }
}
