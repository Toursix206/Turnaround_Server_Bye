package com.toursix.turnaround.service.user;

import com.toursix.turnaround.domain.user.Onboarding;
import com.toursix.turnaround.domain.user.User;
import com.toursix.turnaround.domain.user.repository.UserRepository;
import com.toursix.turnaround.service.user.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserRetrieveService {

    private final UserRepository userRepository;

    public CheckOnboardingInfoResponse checkMyOnboardingInfo(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Onboarding onboarding = user.getOnboarding();
        if (!onboarding.isChecked()) return CheckOnboardingInfoResponse.of(false);
        else return CheckOnboardingInfoResponse.of(true);
    }

    public UserMyPageResponse getUserMyPageInfo(Long userId) {
        return UserMyPageResponse.of(UserServiceUtils.findUserById(userRepository, userId));
    }

    public UserSettingResponse getUserMyPageSettingInfo(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return UserSettingResponse.of(user.getSetting());
    }

    public List<ContactUsResponse> getUserMyPageContactInfo() {
        return Arrays
                .stream(ContactUs.values())
                .map(ContactUsResponse::new)
                .collect(Collectors.toList());
    }
}
