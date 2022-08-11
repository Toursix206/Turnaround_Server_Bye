package com.toursix.turnaround.service.user.dto.request;

import com.toursix.turnaround.domain.user.UserSocialType;
import com.toursix.turnaround.external.client.kakao.dto.response.KakaoAccountInfoResponse;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserDto {

    private String socialId;

    private UserSocialType socialType;

    private KakaoAccountInfoResponse kakaoAccountInfo;

    public static CreateUserDto of(String socialId, UserSocialType socialType, KakaoAccountInfoResponse kakaoAccountInfo) {
        return new CreateUserDto(socialId, socialType, kakaoAccountInfo);
    }
}
