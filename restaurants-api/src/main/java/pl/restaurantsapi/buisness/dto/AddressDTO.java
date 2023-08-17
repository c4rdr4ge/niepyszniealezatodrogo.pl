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
    private String country;
    private String city;
    private String postalCode;
    private String street;
}
