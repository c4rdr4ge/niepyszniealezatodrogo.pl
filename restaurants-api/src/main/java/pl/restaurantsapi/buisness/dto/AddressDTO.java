package pl.restaurantsapi.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Integer addressId;
    private String addressCountry;
    private String addressCity;
    private String addressPostalCode;
    private String addressStreet;
}
