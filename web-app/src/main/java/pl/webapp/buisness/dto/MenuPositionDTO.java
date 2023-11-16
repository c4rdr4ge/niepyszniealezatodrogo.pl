package pl.webapp.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.webapp.consumer.restaurant_api_dtos.RESTDishDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTMenuDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuPositionDTO {

    private Integer menuPositionId;
    private RESTMenuDTO menu;
    private DishDTO dish;
}
