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
import com.toursix.turnaround.external.client.kakao.dto.response.KakaoAccountInfoResponse;
import com.toursix.turnaround.service.user.dto.request.CreateUserDto;
import com.toursix.turnaround.service.user.dto.request.SetOnboardingInfoRequestDto;
import com.toursix.turnaround.service.user.dto.request.UpdateUserSettingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

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
        KakaoAccountInfoResponse kakaoAccountInfo = request.getKakaoAccountInfo();
        String email = (kakaoAccountInfo.isHasEmail()) ? kakaoAccountInfo.getEmail() : "";
        Room room = roomRepository.save(Room.newInstance());
        Onboarding onboarding = onboardingRepository.save(Onboarding.newInstance(email, room));
        User user = userRepository.save(User.newInstance(request.getSocialId(), request.getSocialType(), onboarding, settingRepository.save(Setting.newInstance()), pointRepository.save(Point.newInstance())));
        return user.getId();
    }

    //TODO - 인증된 전화번호인지 검증하는 로직 추가
    @Transactional
    public void setOnboardingInfo(SetOnboardingInfoRequestDto request, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Onboarding onboarding = user.getOnboarding();
        onboarding.setInfo(request.getName(), request.getPhoneNumber(), request.getGender(), request.getCleanAbility(), request.getAddress(), request.getDetailAddress(), request.getGatePassword());
        onboardingRepository.save(onboarding);
    }

    @Transactional
    public void updateUserMyPageSettingInfo(@Valid UpdateUserSettingRequestDto request, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Setting setting = user.getSetting();
        setting.updateInfo(request);
        settingRepository.save(setting);
    }
}
