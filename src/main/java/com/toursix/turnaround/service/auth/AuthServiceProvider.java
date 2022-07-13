package com.toursix.turnaround.service.auth;

import com.toursix.turnaround.domain.user.UserSocialType;
import com.toursix.turnaround.service.auth.impl.AppleAuthService;
import com.toursix.turnaround.service.auth.impl.KaKaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AuthServiceProvider {

    private static final Map<UserSocialType, AuthService> authServiceMap = new HashMap<>();

    private final AppleAuthService appleAuthService;
    private final KaKaoAuthService kaKaoAuthService;

    @PostConstruct
    void initializeAuthServicesMap() {
        authServiceMap.put(UserSocialType.APPLE, appleAuthService);
        authServiceMap.put(UserSocialType.KAKAO, kaKaoAuthService);
    }

    public AuthService getAuthService(UserSocialType socialType) {
        return authServiceMap.get(socialType);
    }
}
