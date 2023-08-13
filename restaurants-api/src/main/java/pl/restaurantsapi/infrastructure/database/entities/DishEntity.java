package pl.restaurantsapi.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"dishName", "dishDescription", "dishWeight", "dishPhotoUrl", "dishPrice"})
@EqualsAndHashCode(of = "dishId")
@Table(name = "dish")
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Integer dishId;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "dish_description")
    private String dishDescription;

    @Column(name = "dish_weight")
    private String dishWeight;

    @Column(name = "dish_photo_url")
    private String dishPhotoUrl;

    @Column(name = "dish_price")
    private BigDecimal dishPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_type_id")
    private KitchenTypeEntity kitchenType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish", cascade = CascadeType.ALL)
    private Set<MenuPositionEntity> menuPositionEntities;
}
