package com.toursix.turnaround.domain.activity.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toursix.turnaround.domain.activity.Activity;
import com.toursix.turnaround.domain.activity.ActivityCategory;
import com.toursix.turnaround.domain.activity.ActivityPaymentStatus;
import com.toursix.turnaround.domain.activity.ActivityReview;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static com.toursix.turnaround.domain.activity.QActivity.activity;
import static com.toursix.turnaround.domain.activity.QActivityReview.activityReview;

@RequiredArgsConstructor
public class ActivityRepositoryImpl implements ActivityRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Activity> findActivitiesByFilterConditionUsingPaging(ActivityPaymentStatus paymentStatus, @Nullable ActivityCategory category, Pageable pageable) {
        List<OrderSpecifier> orders = getAllOrderSpecifiersByActivity(pageable);
        List<Activity> activities = queryFactory
                .selectFrom(activity).distinct()
                .where(
                        eqPaymentStatus(paymentStatus),
                        eqCategory(category)
                )
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(activities, pageable, queryFactory
                .selectFrom(activity).distinct()
                .where(
                        eqPaymentStatus(paymentStatus),
                        eqCategory(category)
                ).fetch().size());
    }

    @Override
    public Activity findActivityById(Long id) {
        return queryFactory
                .selectFrom(activity)
                .where(activity.id.eq(id))
                .fetchOne();

    }

    @Override
    public Page<ActivityReview> findActivityReviewsUsingPaging(Activity activity, Pageable pageable) {
        List<OrderSpecifier> orders = getAllOrderSpecifiersByActivityReview(pageable);
        List<ActivityReview> activityReviews = queryFactory
                .selectFrom(activityReview).distinct()
                .where(
                        activityReview.activity.eq(activity),
                        activityReview.isWritten.eq(true)
                )
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(activityReviews, pageable, queryFactory
                .selectFrom(activityReview).distinct()
                .where(
                        activityReview.activity.eq(activity),
                        activityReview.isWritten.eq(true)
                ).fetch().size());
    }

    private BooleanExpression eqPaymentStatus(ActivityPaymentStatus paymentStatus) {
        return activity.paymentStatus.eq(paymentStatus);
    }

    private BooleanExpression eqCategory(ActivityCategory category) {
        if (category == null) {
            return null;
        }
        return activity.category.eq(category);
    }

    private List<OrderSpecifier> getAllOrderSpecifiersByActivity(Pageable pageable) {
        List<OrderSpecifier> orders = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Path<Object> fieldPath = Expressions.path(Object.class, activity, order.getProperty());
            orders.add(new OrderSpecifier(direction, fieldPath));
        }
        return orders;
    }

    private List<OrderSpecifier> getAllOrderSpecifiersByActivityReview(Pageable pageable) {
        List<OrderSpecifier> orders = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Path<Object> fieldPath = Expressions.path(Object.class, activityReview, order.getProperty());
            orders.add(new OrderSpecifier(direction, fieldPath));
        }
        return orders;
    }
}
