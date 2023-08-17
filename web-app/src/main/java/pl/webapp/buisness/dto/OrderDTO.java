package pl.webapp.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer orderId;
    private String orderNumber;
    private Integer orderAddressId;
    private OffsetDateTime orderDateTime;
    private Boolean orderDelivered;
    private OffsetDateTime orderDeliveredDateTime;
    private String orderAddInfo;
    private Integer orderRestaurantId;
    private UserDTO user;
}
