package com.toursix.turnaround.common.dto;

import com.toursix.turnaround.common.success.SuccessCode;
import lombok.*;
import org.springframework.http.ResponseEntity;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {

    public static final ResponseEntity<String> NO_CONTENT = success(SuccessCode.NO_CONTENT_SUCCESS, "");

    private int status;
    private boolean success;
    private String message;
    private T data;

    public static <T> ResponseEntity<T> success(SuccessCode successCode, T data) {
        return (ResponseEntity<T>) ResponseEntity.status(successCode.getStatus())
                .body(new SuccessResponse(successCode.getStatus(), true, successCode.getMessage(), data));
    }
}
