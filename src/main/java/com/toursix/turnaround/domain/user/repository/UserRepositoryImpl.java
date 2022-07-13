package com.toursix.turnaround.domain.user.repository;

import com.toursix.turnaround.domain.user.User;
import com.toursix.turnaround.domain.user.UserSocialType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.toursix.turnaround.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType) {
        return queryFactory.selectOne()
                .from(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType)
                ).fetchFirst() != null;
    }

    @Override
    public User findUserById(Long id) {
        return queryFactory
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    @Override
    public User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType) {
        return queryFactory
                .selectFrom(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType)
                )
                .fetchOne();
    }
}
