package kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.hhplus.be.server.coupon.domain.entity.QCouponTemplate.couponTemplate;

@Repository
@RequiredArgsConstructor
public class TemplateQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public long decreaseRemainCount(long templateId) {

        return queryFactory.update(couponTemplate)
                .set(couponTemplate.remainCount, couponTemplate.remainCount.subtract(1))
                .where(couponTemplate.id.eq(templateId)
                  .and(couponTemplate.remainCount.gt(0)))
                .execute();
    }

}
