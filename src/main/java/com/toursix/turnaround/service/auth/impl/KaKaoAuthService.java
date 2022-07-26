package com.toursix.turnaround.service.auth.impl;

import com.toursix.turnaround.common.util.HttpHeaderUtils;
import com.toursix.turnaround.domain.user.User;
import com.toursix.turnaround.domain.user.UserSocialType;
import com.toursix.turnaround.domain.user.repository.UserRepository;
import com.toursix.turnaround.external.client.kakao.KakaoApiClient;
import com.toursix.turnaround.external.client.kakao.dto.response.KakaoProfileResponse;
import com.toursix.turnaround.service.auth.AuthService;
import com.toursix.turnaround.service.auth.dto.request.LoginDto;
import com.toursix.turnaround.service.user.UserService;
import com.toursix.turnaround.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KaKaoAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.KAKAO;

    private final KakaoApiClient kaKaoApiCaller;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long login(LoginDto request) {
        KakaoProfileResponse response = kaKaoApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType);
        if (user == null) return userService.registerUser(request.toCreateUserDto(response.getId()));
        return user.getId();
    }
}
