package pl.restaurantsapi.buisness.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\+\\s\\d\\d\\d\\s\\d\\d\\d\\s\\d\\d\\d$")
    private String restaurantOwnerPhone;
    @Email
    private String restaurantOwnerEmail;
    @Min(10)
    @Max(10)
    private String restaurantOwnerNip;
    private AddressDTO address;
}
