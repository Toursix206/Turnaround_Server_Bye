package com.toursix.turnaround.service.user.dto.request;

import com.toursix.turnaround.domain.user.UserSocialType;
import com.toursix.turnaround.external.client.kakao.dto.response.KakaoProfileResponse;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserDto {

    private String socialId;

    private UserSocialType socialType;

    private KakaoProfileResponse.KakaoAccount kakao_account;

    public static CreateUserDto of(String socialId, UserSocialType socialType, KakaoProfileResponse.KakaoAccount kakao_account) {
        return new CreateUserDto(socialId, socialType, kakao_account);
    }
}
