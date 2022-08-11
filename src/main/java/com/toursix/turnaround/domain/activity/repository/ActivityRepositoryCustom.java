package com.toursix.turnaround.domain.activity.repository;

import com.toursix.turnaround.domain.activity.Activity;
import com.toursix.turnaround.domain.activity.ActivityCategory;
import com.toursix.turnaround.domain.activity.ActivityPaymentStatus;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityRepositoryCustom {

    Page<Activity> findActivitiesByFilterConditionUsingPaging(ActivityPaymentStatus paymentStatus, @Nullable ActivityCategory category, Pageable pageable);

    Activity findActivityById(Long id);
}
