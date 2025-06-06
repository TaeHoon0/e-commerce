package kr.hhplus.be.server.coupon.infrastructure.persistence;


import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.IntStream;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponCommandRepository;
import kr.hhplus.be.server.global.utils.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponCommandRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static int BATCH_SIZE = 500;

    @Override
    @Transactional
    public void bulkInsert(UserCoupon userCoupon, int count) {

        System.out.println(
            "count : " + count
            + "coupon : " + userCoupon.getName() + userCoupon.getStatus() + userCoupon.getDiscountAmount()
        );


        String sql =
              "INSERT INTO tb_user_coupon "
            + "(tuc_name, tuc_status, tuc_discount_amount, tuc_minimum_price, tuc_expire_date, tuc_reg_date, tuc_mod_date, tuc_tcp_key) "
            + "VALUES (?, ?, ?, ?, ?, NOW(), NOW(), ?)";

        List<Integer> indexes = IntStream.range(0, count).boxed().toList();

        jdbcTemplate.batchUpdate(
            sql,
            indexes,
            BATCH_SIZE,
            (PreparedStatement ps, Integer idx) -> {
                ps.setString(1, userCoupon.getName());
                ps.setString(2, userCoupon.getStatus().name());
                ps.setBigDecimal(3, userCoupon.getDiscountAmount());
                JdbcUtils.setNullableBigDecimal(ps, 4, userCoupon.getMinimumPrice());
                JdbcUtils.setNullableTimestamp(ps, 5, userCoupon.getExpireDate());
                ps.setLong(6, userCoupon.getCouponTemplate().getId());
            }
        );
    }
}
