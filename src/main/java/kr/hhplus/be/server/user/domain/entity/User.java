package kr.hhplus.be.server.user.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import kr.hhplus.be.server.user.domain.UserType;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tu_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tu_mod_date"))
})
@Table(name = "tb_user")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tu_key", nullable = false)
    private Long id;

    @Column(name = "tu_email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "tu_password", length = 255, nullable = false)
    private String password;

    @Column(name = "tu_name", length = 30, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "tu_type", length = 6, nullable = false)
    private UserType type;

    @Column(name = "tu_last_login_date")
    private LocalDateTime lastLoginDate;


    public static User create(String email, String password, String name, UserType type) {

        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .type(type)
                .build();
    }

    public void updateLoginDate() {
        this.lastLoginDate = LocalDateTime.now();
    }
}
