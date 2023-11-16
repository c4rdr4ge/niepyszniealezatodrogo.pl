package pl.restaurantsapi.buisness.dto.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.MenuPositionDTO;
import pl.restaurantsapi.buisness.services.CategoryService;
import pl.restaurantsapi.buisness.services.KitchenTypeService;
import pl.restaurantsapi.infrastructure.database.entities.MenuPositionEntity;
import pl.restaurantsapi.infrastructure.database.repositories.DishRepository;
import pl.restaurantsapi.infrastructure.database.repositories.MenuRepository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuPositionMapper {

    DishMapper dishMapper = new DishMapperImpl();
    MenuMapper menuMapper = new MenuMapperImpl();

    default MenuPositionDTO map(MenuPositionEntity menuPositionEntity) {
        return MenuPositionDTO.builder()
                .menuPositionId(menuPositionEntity.getMenuPositionId())
                .dish(dishMapper.map(menuPositionEntity.getDish()))
                .menu(menuMapper.map(menuPositionEntity.getMenu()))
                .build();
    }

    default MenuPositionEntity map(MenuPositionDTO menuPositionDTO,
                                   CategoryService categoryService,
                                   KitchenTypeService kitchenTypeService,
                                   DishRepository dishRepository,
                                   MenuRepository menuRepository){
        return MenuPositionEntity.builder()
                .menuPositionId(menuPositionDTO.getMenuPositionId())
                .dish(dishRepository.findById(dishMapper.map(menuPositionDTO.getDish(), categoryService, kitchenTypeService).getDishId())
                        .orElseThrow(() -> new RuntimeException("Dish with this id not exist")))
                .menu(menuRepository.findById(menuMapper.map(menuPositionDTO.getMenu()).getMenuId())
                        .orElseThrow(() -> new RuntimeException("Menu with this id not exist")))
                .build();
    }
}
