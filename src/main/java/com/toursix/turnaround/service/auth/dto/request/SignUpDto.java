package com.toursix.turnaround.service.auth.dto.request;

import com.toursix.turnaround.domain.user.UserSocialType;
import com.toursix.turnaround.service.user.dto.request.CreateUserDto;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpDto {

    private UserSocialType socialType;

    private String token;

    public static SignUpDto of(UserSocialType socialType, String token) {
        return new SignUpDto(socialType, token);
    }

    public CreateUserDto toCreateUserDto(String socialId) {
        return CreateUserDto.of(socialId, socialType);
    }
}
