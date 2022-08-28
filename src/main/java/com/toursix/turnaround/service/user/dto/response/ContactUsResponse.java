package com.toursix.turnaround.service.user.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ContactUsResponse {
    private final String name;
    private final String redirectUrl;

    public ContactUsResponse(ContactUs contactUs) {
        this.name = contactUs.getKey();
        this.redirectUrl = contactUs.getValue();
    }
}
