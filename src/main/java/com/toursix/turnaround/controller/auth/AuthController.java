package com.toursix.turnaround.controller.auth;

import com.toursix.turnaround.common.dto.ErrorResponse;
import com.toursix.turnaround.common.dto.SuccessResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.config.resolver.UserId;
import com.toursix.turnaround.controller.auth.dto.request.LoginRequestDto;
import com.toursix.turnaround.controller.auth.dto.response.LoginResponse;
import com.toursix.turnaround.service.auth.AuthPhoneService;
import com.toursix.turnaround.service.auth.AuthService;
import com.toursix.turnaround.service.auth.AuthServiceProvider;
import com.toursix.turnaround.service.auth.CreateTokenService;
import com.toursix.turnaround.service.auth.dto.request.AuthPhoneCheckRequestDto;
import com.toursix.turnaround.service.auth.dto.request.AuthPhoneRequestDto;
import com.toursix.turnaround.service.auth.dto.request.TokenRequestDto;
import com.toursix.turnaround.service.auth.dto.response.TokenResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(tags = "Auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceProvider authServiceProvider;
    private final CreateTokenService createTokenService;
    private final AuthPhoneService authPhoneService;

    @ApiOperation(
            value = "로그인 페이지 - 로그인을 요청합니다.",
            notes = "카카오 로그인, 애플 로그인을 요청합니다.\n" +
                    "최초 로그인의 경우 회원가입 처리 후 로그인됩니다.\n" +
                    "socialType - KAKAO (카카오), APPLE (애플)\n"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "로그인 성공입니다."),
            @ApiResponse(
                    code = 400,
                    message = "1. 유저의 socialType 를 입력해주세요.\n"
                            + "2. access token 을 입력해주세요.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "유효하지 않은 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "이미 해당 계정으로 회원가입하셨습니다.\n로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @PostMapping("/v1/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequestDto request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        Long userId = authService.login(request.toServiceDto());

        TokenResponseDto tokenInfo = createTokenService.createTokenInfo(userId);
        return SuccessResponse.success(SuccessCode.LOGIN_SUCCESS, LoginResponse.of(userId, tokenInfo));
    }

    @ApiOperation(
            value = "JWT 인증 - Access Token 을 갱신합니다.",
            notes = "만료된 Access Token 을 Refresh Token 으로 갱신합니다.\n" +
                    "Refresh Token 이 유효하지 않거나 만료된 경우 갱신에 실패합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "토큰 갱신 성공입니다."),
            @ApiResponse(
                    code = 400,
                    message = "1. access token 을 입력해주세요.\n"
                            + "2. refresh token 을 입력해주세요.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @PostMapping("/v1/auth/refresh")
    public ResponseEntity<TokenResponseDto> reissue(@Valid @RequestBody TokenRequestDto request) {
        return SuccessResponse.success(SuccessCode.REISSUE_TOKEN_SUCCESS, createTokenService.reissueToken(request));
    }

    @ApiOperation(
            value = "[인증] 온보딩 페이지 - 전화번호에 대한 인증번호를 요청합니다.",
            notes = "유효기간 3분의 인증번호를 문자메시지로 전송합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = ""),
            @ApiResponse(
                    code = 400,
                    message = "1. 전화번호를 입력해주세요. (phoneNumber)\n"
                            + "2. 전화번호 양식에 맞게 입력해주세요. (010-1234-5678) (phoneNumber)",
                    response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "1. 예상치 못한 서버 에러가 발생하였습니다.\n"
                            + "2. 인증번호 전송 실패입니다.",
                    response = ErrorResponse.class)
    })
    @Auth
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/v1/auth/phone")
    public ResponseEntity<String> authPhone(@Valid @RequestBody AuthPhoneRequestDto request, @ApiIgnore @UserId Long userId) {
        authPhoneService.authPhone(request, userId);
        return SuccessResponse.NO_CONTENT;
    }

    @ApiOperation(
            value = "[인증] 온보딩 페이지 - 전화번호에 대한 인증번호를 확인합니다.",
            notes = "유효기간 3분의 인증번호가 일치하는지 확인합니다.\n" +
                    "인증번호가 일치하지 않거나 만료된 경우, 400 에러 (인증번호를 다시 입력해주세요.) 를 전달합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = ""),
            @ApiResponse(
                    code = 400,
                    message = "1. 전화번호를 입력해주세요. (phoneNumber)\n"
                            + "2. 전화번호 양식에 맞게 입력해주세요. (010-1234-5678) (phoneNumber)\n"
                            + "3. 인증번호를 입력해주세요. (authNumber)\n"
                            + "4. 인증번호를 다시 입력해주세요.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/v1/auth/phone/check")
    public ResponseEntity<String> authPhoneCheck(@Valid @RequestBody AuthPhoneCheckRequestDto request, @ApiIgnore @UserId Long userId) {
        authPhoneService.authPhoneCheck(request, userId);
        return SuccessResponse.NO_CONTENT;
    }
}
