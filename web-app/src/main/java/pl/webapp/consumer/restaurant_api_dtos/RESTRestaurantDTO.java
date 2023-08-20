package pl.webapp.consumer.restaurant_api_dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RESTRestaurantDTO {

    private Integer restaurantId;
    private String restaurantName;
    private String restaurantPhone;
    private BigDecimal restaurantRating;
    private RESTKitchenTypeDTO kitchenType;
    private RESTAddressDTO address;
    private RESTMenuDTO menu;
    private RESTRestaurantOwnerDTO restaurantOwner;
}
