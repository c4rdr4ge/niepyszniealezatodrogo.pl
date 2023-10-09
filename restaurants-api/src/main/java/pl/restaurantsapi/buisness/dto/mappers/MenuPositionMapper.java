package pl.restaurantsapi.buisness.dto.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.MenuPositionDTO;
import pl.restaurantsapi.infrastructure.database.entities.MenuPositionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuPositionMapper {

    DishMapper dishMapper = new DishMapperImpl();
    MenuMapper menuMapper = new MenuMapperImpl();

    default MenuPositionDTO map(MenuPositionEntity menuPositionEntity) {
        return MenuPositionDTO.builder()
                .dish(dishMapper.map(menuPositionEntity.getDish()))
                .menu(menuMapper.map(menuPositionEntity.getMenu()))
                .build();
    }

    default MenuPositionEntity map(MenuPositionDTO menuPositionDTO){
        return MenuPositionEntity.builder()
                .dish(dishMapper.map(menuPositionDTO.getDish()))
                .menu(menuMapper.map(menuPositionDTO.getMenu()))
                .build();
    }
}
