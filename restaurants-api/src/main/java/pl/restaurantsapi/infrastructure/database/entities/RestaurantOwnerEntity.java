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
@ToString(of = {
        "restaurantOwnerName",
        "restaurantOwnerSurname",
        "restaurantOwnerPhone",
        "restaurantOwnerEmail",
        "restaurantOwnerNip"
})
@EqualsAndHashCode(of = {"restaurantOwnerNip", "restaurantOwnerEmail"})
@Table(name = "restaurant_owner")
public class RestaurantOwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_owner_id")
    private Integer restaurantOwnerId;

    @Column(name = "restaurant_owner_name")
    private String restaurantOwnerName;

    @Column(name = "restaurant_owner_surname")
    private String restaurantOwnerSurname;

    @Column(name = "restaurant_owner_phone")
    private String restaurantOwnerPhone;

    @Column(name = "restaurant_owner_email", unique = true)
    private String restaurantOwnerEmail;

    @Column(name = "restaurant_owner_nip", unique = true)
    private String restaurantOwnerNip;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_owner_address_id")
    private AddressEntity address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurantOwner", cascade = CascadeType.ALL)
    private Set<RestaurantEntity> restaurantEntities;
}
