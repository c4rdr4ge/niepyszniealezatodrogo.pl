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
@ToString
@EqualsAndHashCode(of = "basketId")
@Table(name = "basket")
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Integer basketId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "basket", cascade = CascadeType.ALL)
    private Set<OrderEntity> orderEntities;
}
