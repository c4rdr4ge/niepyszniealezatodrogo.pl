package pl.webapp.infrastructure.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"userUsername", "userName", "userSurname", "userPhone", "userEmail"})
@EqualsAndHashCode(of = "userUsername")
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_username", unique = true)
    private String userUsername;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
    private String userSurname;

    @Pattern(regexp = "^\\+\\s\\d\\d\\d\\s\\d\\d\\d\\s\\d\\d\\d$")
    @Column(name = "user_phone")
    private String userPhone;

    @Email
    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_address_id")
    private Integer userAddressId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<OrderEntity> orderEntities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRoleEntity> userRoleEntities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<OrderHistoryEntity> orderHistoryEntities;
}
