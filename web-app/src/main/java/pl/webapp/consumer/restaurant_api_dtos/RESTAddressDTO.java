package pl.webapp.consumer.restaurant_api_dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RESTAddressDTO {

    private Integer addressId;
    private String addressCountry;
    private String addressCity;
    private String addressPostalCode;
    private String addressStreet;
}
