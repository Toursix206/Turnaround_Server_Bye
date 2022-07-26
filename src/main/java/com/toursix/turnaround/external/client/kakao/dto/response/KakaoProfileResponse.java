package com.toursix.turnaround.external.client.kakao.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoProfileResponse {

    private String id;

    private KakaoAccount kakao_account;

    private KakaoProfileResponse(String id, KakaoAccount kakao_account) {
        this.id = id;
        this.kakao_account = kakao_account;
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public class KakaoAccount {
        private String name;
        private boolean has_email;
        private String email;
        private boolean has_phone_number;
        private String phone_number;
    }
}
