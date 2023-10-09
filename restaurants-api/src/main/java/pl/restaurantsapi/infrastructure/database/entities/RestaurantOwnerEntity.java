package pl.restaurantsapi.infrastructure.database.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^\\+\\s\\d\\d\\d\\s\\d\\d\\d\\s\\d\\d\\d$")
    @Column(name = "restaurant_owner_phone")
    private String restaurantOwnerPhone;

    @Email
    @Column(name = "restaurant_owner_email", unique = true)
    private String restaurantOwnerEmail;

    @Min(10)
    @Max(10)
    @Column(name = "restaurant_owner_nip", unique = true)
    private String restaurantOwnerNip;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_owner_address_id")
    private AddressEntity address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurantOwner", cascade = CascadeType.ALL)
    private Set<RestaurantEntity> restaurantEntities;
}
