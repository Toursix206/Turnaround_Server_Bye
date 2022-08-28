package com.toursix.turnaround.service.user.dto.response;

import com.toursix.turnaround.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContactUs implements EnumModel {

    //TODO redirectUrl 바꾸기
    CUSTOMER_CENTER("고객센터 연결링크"),
    STORE("스토어 연결링크");

    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
