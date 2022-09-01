package com.toursix.turnaround.controller.user;

import com.toursix.turnaround.common.dto.ErrorResponse;
import com.toursix.turnaround.common.dto.SuccessResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.resolver.UserId;
import com.toursix.turnaround.service.user.UserService;
import com.toursix.turnaround.service.user.dto.request.SetOnboardingInfoRequestDto;
import com.toursix.turnaround.service.user.dto.request.UpdateUserSettingRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @ApiOperation(
            value = "[인증] 온보딩 페이지 - 나의 온보딩 정보를 설정합니다.",
            notes = "최초 회원가입시 온보딩 정보를 설정합니다.\n" +
                    "이름과 인증이 완료된 전화번호, 서비스에 필요한 정보들을 입력받습니다.\n" +
                    "인증되지 않은 전화번호이거나 인증 후 24시간이 지난 경우, 401 에러 (인증되지 않은 전화번호입니다.) 를 전달합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "온보딩 정보 설정 성공입니다."),
            @ApiResponse(
                    code = 400,
                    message = "1. 이름을 입력해주세요. (name)\n"
                            + "2. 전화번호를 입력해주세요. (phoneNumber)\n"
                            + "3. 전화번호 형식으로 입력해주세요. (phoneNumber)\n"
                            + "4. 성별을 입력해주세요. (gender)\n"
                            + "5. 부지런한 정도를 입력해주세요. (cleanAbility)\n"
                            + "6. 주소를 입력해주세요. (address)\n"
                            + "7. 상세주소를 입력해주세요. (detailAddress)\n"
                            + "8. 공동현관 비밀번호를 입력해주세요. (gatePassword)",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 401,
                    message = "1. 토큰이 만료되었습니다. 다시 로그인 해주세요.\n"
                            + "2. 인증되지 않은 전화번호입니다.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PostMapping("/v1/user/onboarding")
    public ResponseEntity<String> setOnboardingInfo(@Valid @RequestBody SetOnboardingInfoRequestDto request, @ApiIgnore @UserId Long userId) {
        userService.setOnboardingInfo(request, userId);
        return SuccessResponse.success(SuccessCode.SET_ONBOARDING_SUCCESS, null);
    }

    //TODO 200 -> 204로 수정 필요
    @ApiOperation(
            value = "[인증] 마이 페이지 - 나의 설정 정보를 수정합니다.",
            notes = "혜택 및 이벤트 정보 수신 동의, 활동 푸시 알림 수신 동의, 이벤트 참여 정보 제공 동의를 수정합니다.\n" +
                    "최소 1개의 값(boolean)을 보내주어야 합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "나의 설정 정보 수정 성공입니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PatchMapping("/v1/user/setting")
    public ResponseEntity<String> updateUserMyPageSettingInfo(@Valid @RequestBody UpdateUserSettingRequestDto request, @ApiIgnore @UserId Long userId) {
        userService.updateUserMyPageSettingInfo(request, userId);
        return SuccessResponse.success(SuccessCode.UPDATE_MYPAGE_SETTINGS_SUCCESS, null);
    }
}
