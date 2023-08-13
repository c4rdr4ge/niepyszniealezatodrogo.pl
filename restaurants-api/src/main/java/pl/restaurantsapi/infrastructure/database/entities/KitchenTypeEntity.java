package pl.restaurantsapi.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"kitchenTypeId", "kitchenTypeName"})
@EqualsAndHashCode(of = "kitchenTypeName")
@Table(name = "kitchen_type")
public class KitchenTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_type_id")
    private Integer kitchenTypeId;

    @Column(name = "kitchen_type_name")
    private String kitchenTypeName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kitchenType", cascade = CascadeType.ALL)
    private Set<MenuEntity> menuEntities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kitchenType", cascade = CascadeType.ALL)
    private Set<RestaurantEntity> restaurantEntities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kitchenType", cascade = CascadeType.ALL)
    private Set<DishEntity> dishEntities;
}
