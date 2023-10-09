package pl.webapp.buisness.dto;

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

    private String dishName;
    private String dishDescription;
    private Integer dishWeight;
    private String dishPhotoURL;
    private BigDecimal dishPrice;
    private String category;
    private String kitchenType;
}
