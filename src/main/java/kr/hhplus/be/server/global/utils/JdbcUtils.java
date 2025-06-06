package kr.hhplus.be.server.global.utils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

public class JdbcUtils {

    public static void setNullableBigDecimal(PreparedStatement ps, int idx, BigDecimal value) throws SQLException {
        if (value != null) {
            ps.setBigDecimal(idx, value);
        } else {
            // Types.NUMERIC 또는 DECIMAL 등, 컬럼 정의에 맞춰 알맞은 SQL 타입 사용
            ps.setNull(idx, Types.DECIMAL);
        }
    }

    // String 전용
    public static void setNullableString(PreparedStatement ps, int idx, String value) throws SQLException {
        if (value != null && !value.isEmpty()) {
            ps.setString(idx, value);
        } else {
            ps.setNull(idx, Types.VARCHAR);
        }
    }

    // LocalDateTime ⇒ java.sql.Timestamp 전환
    public static void setNullableTimestamp(PreparedStatement ps, int idx, LocalDateTime value) throws SQLException {
        if (value != null) {
            ps.setTimestamp(idx, Timestamp.valueOf(value));
        } else {
            ps.setNull(idx, Types.TIMESTAMP);
        }
    }

    // Integer
    public static void setNullableInt(PreparedStatement ps, int idx, Integer value) throws SQLException {
        if (value != null) {
            ps.setInt(idx, value);
        } else {
            ps.setNull(idx, Types.INTEGER);
        }
    }

    // Long
    public static void setNullableLong(PreparedStatement ps, int idx, Long value) throws SQLException {
        if (value != null) {
            ps.setLong(idx, value);
        } else {
            ps.setNull(idx, Types.BIGINT);
        }
    }

    // Boolean
    public static void setNullableBoolean(PreparedStatement ps, int idx, Boolean value) throws SQLException {
        if (value != null) {
            ps.setBoolean(idx, value);
        } else {
            ps.setNull(idx, Types.BOOLEAN);
        }
    }
}
