package com.toursix.turnaround.service.user;

import com.toursix.turnaround.domain.room.Room;
import com.toursix.turnaround.domain.room.repository.RoomRepository;
import com.toursix.turnaround.domain.user.Onboarding;
import com.toursix.turnaround.domain.user.Point;
import com.toursix.turnaround.domain.user.User;
import com.toursix.turnaround.domain.user.repository.PointRepository;
import com.toursix.turnaround.domain.user.repository.UserRepository;
import com.toursix.turnaround.service.user.dto.response.CheckOnboardingInfoResponse;
import com.toursix.turnaround.service.user.dto.response.UserMyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserRetrieveService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;
    private final RoomRepository roomRepository;

    public CheckOnboardingInfoResponse checkMyOnboardingInfo(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Onboarding onboarding = user.getOnboarding();
        if (!onboarding.isChecked()) return CheckOnboardingInfoResponse.of(false);
        else return CheckOnboardingInfoResponse.of(true);
    }

    public UserMyPageResponse getUserMyPageInfo(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Onboarding onboarding = user.getOnboarding();
        Point point = pointRepository.findPointById(user.getPoint().getId());
        Room room = roomRepository.findRoomById(onboarding.getRoom().getId());
        return UserMyPageResponse.of(onboarding, room, point);
    }
}
