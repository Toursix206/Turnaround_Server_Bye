package com.toursix.turnaround.controller.user;

import com.toursix.turnaround.common.dto.ErrorResponse;
import com.toursix.turnaround.common.dto.SuccessResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.resolver.UserId;
import com.toursix.turnaround.service.user.UserService;
import com.toursix.turnaround.service.user.dto.request.SetOnboardingInfoRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    //TODO - 전화번호 인증 추가 후 명세서 수정
    @ApiOperation(
            value = "[인증] 온보딩 페이지 - 나의 온보딩 정보를 설정합니다.",
            notes = "최초 회원가입시 온보딩 정보를 설정합니다.\n" +
                    "이름과 인증이 완료된 전화번호, 서비스에 필요한 정보들을 입력받습니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "온보딩 정보 설정 성공입니다."),
            @ApiResponse(
                    code = 400,
                    message = "1. 이름을 입력해주세요.\n"
                            + "2. 전화번호를 입력해주세요.\n"
                            + "3. 전화번호 형식으로 입력해주세요.\n"
                            + "4. 성별을 입력해주세요.\n"
                            + "5. 부지런한 정도를 입력해주세요.\n"
                            + "6. 주소를 입력해주세요.\n"
                            + "7. 상세주소를 입력해주세요.\n"
                            + "8. 공동현관 비밀번호를 입력해주세요.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "탈퇴했거나 존재하지 않는 유저입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PostMapping("/v1/user/onboarding")
    public SuccessResponse<String> setOnboardingInfo(@Valid @RequestBody SetOnboardingInfoRequestDto request, @ApiIgnore @UserId Long userId) {
        userService.setOnboardingInfo(request, userId);
        return SuccessResponse.success(SuccessCode.SET_ONBOARDING_SUCCESS, null);
    }
}
