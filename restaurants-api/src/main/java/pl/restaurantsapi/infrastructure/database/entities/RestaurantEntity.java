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
@ToString(of = {"restaurantName", "restaurantPhone", "restaurantRating"})
@EqualsAndHashCode(of = "restaurantName")
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_phone")
    private String restaurantPhone;

    @Column(name = "restaurant_rating")
    private BigDecimal restaurantRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_type_id")
    private KitchenTypeEntity kitchenType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_address_id")
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_menu_id")
    private MenuEntity menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_owner_id")
    private RestaurantOwnerEntity restaurantOwner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<AvailableAddressEntity> availableAddressEntities;
}
