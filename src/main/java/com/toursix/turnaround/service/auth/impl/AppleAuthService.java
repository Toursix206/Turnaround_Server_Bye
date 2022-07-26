package com.toursix.turnaround.service.auth.impl;

import com.toursix.turnaround.domain.user.User;
import com.toursix.turnaround.domain.user.UserSocialType;
import com.toursix.turnaround.domain.user.repository.UserRepository;
import com.toursix.turnaround.external.client.apple.AppleTokenProvider;
import com.toursix.turnaround.service.auth.AuthService;
import com.toursix.turnaround.service.auth.dto.request.LoginDto;
import com.toursix.turnaround.service.user.UserService;
import com.toursix.turnaround.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.APPLE;

    private final AppleTokenProvider appleTokenDecoder;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long login(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, socialId, socialType);
        if (user == null) return userService.registerUser(request.toCreateUserDto(socialId));
        return user.getId();
    }
}
