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
public class DishDTO {

    private Integer dishId;
    private String dishName;
    private String dishDescription;
    private Integer dishWeight;
    private String dishPhotoUrl;
    private BigDecimal dishPrice;
    private CategoryDTO category;
    private KitchenTypeDTO kitchenType;


}
