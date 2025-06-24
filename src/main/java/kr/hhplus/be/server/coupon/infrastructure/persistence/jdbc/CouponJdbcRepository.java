package kr.hhplus.be.server.coupon.infrastructure.persistence.jdbc;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.IntStream;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.global.utils.JdbcUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static int BATCH_SIZE = 500;

    public void bulkInsert(UserCoupon userCoupon, int count) {

        String sql =
            "INSERT INTO tb_user_coupon "
                + "(tuc_name, tuc_status, tuc_type, tuc_discount_amount, tuc_minimum_price, tuc_expire_date, tuc_reg_date, tuc_mod_date, tuc_tcp_key) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW(), ?)";

        List<Integer> indexes = IntStream.range(0, count).boxed().toList();

        jdbcTemplate.batchUpdate(
            sql,
            indexes,
            BATCH_SIZE,
            (PreparedStatement ps, Integer idx) -> {
                ps.setString(1, userCoupon.getName());
                ps.setString(2, userCoupon.getStatus().name());
                ps.setString(3, userCoupon.getType().name());
                ps.setBigDecimal(4, userCoupon.getDiscountAmount());
                JdbcUtil.setNullableBigDecimal(ps, 5, userCoupon.getMinimumPrice());
                JdbcUtil.setNullableTimestamp(ps, 6, userCoupon.getExpireDate());
                ps.setLong(7, userCoupon.getCouponTemplate().getId());
            }
        );
    }

}
