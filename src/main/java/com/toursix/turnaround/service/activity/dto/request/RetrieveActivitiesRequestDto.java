package com.toursix.turnaround.service.activity.dto.request;

import com.toursix.turnaround.domain.activity.ActivityCategory;
import com.toursix.turnaround.domain.activity.ActivityPaymentStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrieveActivitiesRequestDto {

    @ApiModelProperty(value = "무료, 유료 - FREE, PAID", required = true, example = "FREE")
    private ActivityPaymentStatus paymentStatus;

    @ApiModelProperty(value = "카테고리 - 전체일 경우 null")
    @Nullable
    private ActivityCategory category;
}
