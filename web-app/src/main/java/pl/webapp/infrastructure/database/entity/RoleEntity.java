package pl.webapp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "roleName")
@EqualsAndHashCode(of = "roleName")
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRoleEntity> userRoleEntities;
}
