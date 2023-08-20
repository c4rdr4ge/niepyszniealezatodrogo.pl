package pl.webapp.buisness.dto.webdtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierAddressDTO {

    String orderRestaurantName;
    String orderNumber;
    String orderAddressStreet;
    OffsetDateTime orderDateTime;
}
