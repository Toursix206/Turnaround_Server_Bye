package com.toursix.turnaround.service.user;

import com.toursix.turnaround.domain.room.Room;
import com.toursix.turnaround.domain.room.repository.RoomRepository;
import com.toursix.turnaround.domain.user.Onboarding;
import com.toursix.turnaround.domain.user.Point;
import com.toursix.turnaround.domain.user.Setting;
import com.toursix.turnaround.domain.user.User;
import com.toursix.turnaround.domain.user.repository.OnboardingRepository;
import com.toursix.turnaround.domain.user.repository.PointRepository;
import com.toursix.turnaround.domain.user.repository.SettingRepository;
import com.toursix.turnaround.domain.user.repository.UserRepository;
import com.toursix.turnaround.external.client.kakao.dto.response.KakaoProfileResponse;
import com.toursix.turnaround.service.user.dto.request.CreateUserDto;
import com.toursix.turnaround.service.user.dto.request.UpdateOnboardingInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final SettingRepository settingRepository;
    private final PointRepository pointRepository;
    private final OnboardingRepository onboardingRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        KakaoProfileResponse.KakaoAccount kakao_account = request.getKakao_account();
        String email = (kakao_account.isHas_email()) ? kakao_account.getEmail() : "";
        String phoneNumber = (kakao_account.isHas_phone_number()) ? kakao_account.getPhone_number() : "";
        Room room = roomRepository.save(Room.newInstance());
        Onboarding onboarding = onboardingRepository.save(Onboarding.newInstance(kakao_account.getName(), email, phoneNumber, room));
        User user = userRepository.save(User.newInstance(request.getSocialId(), request.getSocialType(), onboarding, settingRepository.save(Setting.newInstance()), pointRepository.save(Point.newInstance())));
        return user.getId();
    }

    @Transactional
    public void setOnboardingInfo(UpdateOnboardingInfoRequestDto request, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Onboarding onboarding = user.getOnboarding();
        onboarding.updateInfo(request.getGender(), request.getCleanAbility(), request.getAddress(), request.getDetailAddress(), request.getGatePassword());
        onboardingRepository.save(onboarding);
    }
}
