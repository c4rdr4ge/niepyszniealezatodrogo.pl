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
@ToString(of = {"addressId", "addressCountry", "addressCity", "addressPostalCode", "addressStreet"})
@EqualsAndHashCode(of = {"addressCountry", "addressCity", "addressPostalCode", "addressStreet"})
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address_country")
    private String addressCountry;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_postal_code")
    private String addressPostalCode;

    @Column(name = "address_street")
    private String addressStreet;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private RestaurantOwnerEntity restaurantOwner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private Set<RestaurantEntity> restaurantEntities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private Set<AvailableAddressEntity> availableAddressEntities;
}
