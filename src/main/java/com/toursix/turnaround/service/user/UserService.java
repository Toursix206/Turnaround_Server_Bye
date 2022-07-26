package com.toursix.turnaround.service.user;

import com.toursix.turnaround.domain.user.Point;
import com.toursix.turnaround.domain.user.Setting;
import com.toursix.turnaround.domain.user.User;
import com.toursix.turnaround.domain.user.repository.PointRepository;
import com.toursix.turnaround.domain.user.repository.SettingRepository;
import com.toursix.turnaround.domain.user.repository.UserRepository;
import com.toursix.turnaround.service.user.dto.request.CreateUserDto;
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

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        User user = userRepository.save(User.newInstance(request.getSocialId(), request.getSocialType(), settingRepository.save(Setting.newInstance()), pointRepository.save(Point.newInstance())));
        return user.getId();
    }
}
