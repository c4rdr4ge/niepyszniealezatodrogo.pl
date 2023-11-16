package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.DishDTO;
import pl.webapp.buisness.dto.webdtos.WebDishDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {

    static DishDTO WebToNormal(WebDishDTO webDishDTO) {
        DishDTO.builder()
                .dishName(webDishDTO.getDishName())
                .dishDescription(webDishDTO.getDishDescription())
                .dishWeight(webDishDTO.getDishWeight())
                .dishPhotoUrl("/images/" + webDishDTO.getDishPhotoFile().getOriginalFilename())
                .dishPrice(webDishDTO.getDishPrice())
                .category(webDishDTO.getCategory())
                .kitchenType(webDishDTO.getKitchenType());

        return null;
    }
}
