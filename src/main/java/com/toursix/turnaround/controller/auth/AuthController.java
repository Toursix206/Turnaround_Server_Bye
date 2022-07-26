package com.toursix.turnaround.controller.auth;

import com.toursix.turnaround.common.dto.ApiResponse;
import com.toursix.turnaround.controller.auth.dto.request.LoginRequestDto;
import com.toursix.turnaround.controller.auth.dto.response.LoginResponse;
import com.toursix.turnaround.service.auth.AuthService;
import com.toursix.turnaround.service.auth.AuthServiceProvider;
import com.toursix.turnaround.service.auth.CreateTokenService;
import com.toursix.turnaround.service.auth.dto.request.TokenRequestDto;
import com.toursix.turnaround.service.auth.dto.response.TokenResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceProvider authServiceProvider;
    private final CreateTokenService createTokenService;

    @ApiOperation("로그인 페이지 - 로그인을 요청합니다")
    @PostMapping("/v1/auth/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequestDto request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        Long userId = authService.login(request.toServiceDto());

        TokenResponseDto tokenInfo = createTokenService.createTokenInfo(userId);
        return ApiResponse.success(LoginResponse.of(userId, tokenInfo));
    }

    @ApiOperation("JWT 인증 - Access Token을 갱신합니다.")
    @PostMapping("/v1/auth/refresh")
    public ApiResponse<TokenResponseDto> reissue(@Valid @RequestBody TokenRequestDto request) {
        return ApiResponse.success(createTokenService.reissueToken(request));
    }
}
