package com.toursix.turnaround.service.auth.dto.request;

import com.toursix.turnaround.domain.user.UserSocialType;
import com.toursix.turnaround.external.client.kakao.dto.response.KakaoAccountInfoResponse;
import com.toursix.turnaround.service.user.dto.request.CreateUserDto;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {

    private UserSocialType socialType;

    private String token;

    public static LoginDto of(UserSocialType socialType, String token) {
        return new LoginDto(socialType, token);
    }

    public CreateUserDto toCreateUserDto(String socialId, KakaoAccountInfoResponse kakaoAccountInfo) {
        return CreateUserDto.of(socialId, socialType, kakaoAccountInfo);
    }
}
