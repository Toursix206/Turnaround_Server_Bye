package com.toursix.turnaround.common.success;

import static com.toursix.turnaround.common.success.SuccessStatusCode.CREATED;
import static com.toursix.turnaround.common.success.SuccessStatusCode.OK;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK
     */
    SUCCESS(OK, "성공입니다."),

    // 인증
    LOGIN_SUCCESS(OK, "로그인 성공입니다."),
    REISSUE_TOKEN_SUCCESS(OK, "토큰 갱신 성공입니다."),

    // 온보딩
    CHECK_ONBOARDING_SUCCESS(OK, "온보딩 등록여부 조회 성공입니다."),
    SET_ONBOARDING_SUCCESS(OK, "온보딩 정보 설정 성공입니다."),

    // 활동
    READ_ACTIVITIES_SUCCESS(OK, "활동 리스트 조회 성공입니다."),
    READ_ACTIVITY_SUCCESS(OK, "활동 조회 성공입니다."),
    READ_ACTIVITY_REVIEWS_SUCCESS(OK, "활동 리뷰 리스트 조회 성공입니다."),

    // 마이페이지
    READ_MYPAGE_SUCCESS(OK, "마이페이지 조회 성공입니다."),
    READ_MYPAGE_SETTINGS_SUCCESS(OK, "나의 설정 정보 조회 성공입니다."),
    UPDATE_MYPAGE_SETTINGS_SUCCESS(OK, "나의 설정 정보 수정 성공입니다."),
    READ_MYPAGE_CONTACT_SUCCESS(OK, "고객센터 정보 조회 성공입니다."),

    /**
     * 201 CREATED
     */
    CREATE_ROOM_DATA_SUCCESS(CREATED, "방 라벨링 데이터 생성 성공입니다."),

    /**
     * 202 ACCEPTED
     */

    /**
     * 204 NO_CONTENT
     */
    ;

    private final SuccessStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
