package pl.restaurantsapi.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOwnerDTO {

    private Integer restaurantOwnerId;
    private String restaurantOwnerName;
    private String restaurantOwnerSurname;
    private String restaurantOwnerPhone;
    private String restaurantOwnerEmail;
    private String restaurantOwnerNip;
    private AddressDTO address;
}
