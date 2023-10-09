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
public class RESTDishDTO {

    private Integer dishId;
    private String dishName;
    private String dishDescription;
    private Integer dishWeight;
    private String dishPhotoUrl;
    private BigDecimal dishPrice;
    private String category;
    private String kitchenType;


}
