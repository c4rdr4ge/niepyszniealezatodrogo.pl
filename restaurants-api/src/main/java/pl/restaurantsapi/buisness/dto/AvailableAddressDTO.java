package pl.restaurantsapi.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableAddressDTO {

    private Integer availableAddressId;
    private RestaurantDTO restaurant;
    private AddressDTO address;
}
