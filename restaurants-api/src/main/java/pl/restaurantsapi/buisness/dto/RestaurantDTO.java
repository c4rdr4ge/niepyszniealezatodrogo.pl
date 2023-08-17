package pl.restaurantsapi.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Integer restaurantId;
    private String restaurantName;
    private String restaurantPhone;
    private BigDecimal restaurantRating;
    private KitchenTypeDTO kitchenType;
    private AddressDTO address;
    private MenuDTO menu;
    private RestaurantOwnerDTO restaurantOwner;
}
