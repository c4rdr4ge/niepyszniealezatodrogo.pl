package pl.webapp.consumer.restaurant_api_dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RESTAvailableAddressDTO {

    private Integer availableAddressId;
    private RESTRestaurantDTO restaurant;
    private RESTAddressDTO address;
}
