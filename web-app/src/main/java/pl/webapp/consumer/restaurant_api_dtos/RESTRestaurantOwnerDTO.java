package pl.webapp.consumer.restaurant_api_dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RESTRestaurantOwnerDTO {

    private Integer restaurantOwnerId;
    private String restaurantOwnerName;
    private String restaurantOwnerSurname;
    private String restaurantOwnerPhone;
    private String restaurantOwnerEmail;
    private String restaurantOwnerNip;
    private RESTAddressDTO address;
}
