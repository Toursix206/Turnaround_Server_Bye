package com.toursix.turnaround.common.success;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.toursix.turnaround.common.success.SuccessStatusCode.OK;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK
    SUCCESS(OK, "성공입니다."),

    // 인증
    LOGIN_SUCCESS(OK, "로그인 성공입니다."),
    REISSUE_TOKEN_SUCCESS(OK, "토큰 갱신 성공입니다."),

    // 온보딩
    CHECK_ONBOARDING_SUCCESS(OK, "온보딩 등록여부 조회 성공입니다."),

    // 활동
    READ_ACTIVITIES_SUCCESS(OK, "활동 리스트 조회 성공입니다."),

    // 마이페이지
    READ_MYPAGE_SUCCESS(OK, "마이페이지 조회 성공입니다."),

    // 201 CREATED

    // 202 ACCEPTED

    // 204 NO_CONTENT
    ;

    private final SuccessStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
