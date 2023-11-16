package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pl.restaurantsapi.buisness.dto.DishDTO;
import pl.restaurantsapi.buisness.services.CategoryService;
import pl.restaurantsapi.buisness.services.KitchenTypeService;
import pl.restaurantsapi.infrastructure.database.entities.CategoryEntity;
import pl.restaurantsapi.infrastructure.database.entities.DishEntity;
import pl.restaurantsapi.infrastructure.database.entities.KitchenTypeEntity;
import pl.restaurantsapi.infrastructure.database.repositories.CategoryRepository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface DishMapper {

    default DishDTO map(DishEntity dishEntity) {
        return DishDTO.builder()
                .dishId(dishEntity.getDishId())
                .dishName(dishEntity.getDishName())
                .dishDescription(dishEntity.getDishDescription())
                .dishWeight(dishEntity.getDishWeight())
                .dishPhotoUrl(dishEntity.getDishPhotoUrl())
                .dishPrice(dishEntity.getDishPrice())
                .category(dishEntity.getCategory().getCategoryName())
                .kitchenType(dishEntity.getKitchenType().getKitchenTypeName())
                .build();
    }

    default DishEntity map(DishDTO dishDTO, CategoryService categoryService, KitchenTypeService kitchenTypeService) {
        return DishEntity.builder()
                .dishId(dishDTO.getDishId())
                .dishName(dishDTO.getDishName())
                .dishDescription(dishDTO.getDishDescription())
                .dishWeight(dishDTO.getDishWeight())
                .dishPhotoUrl(dishDTO.getDishPhotoUrl())
                .dishPrice(dishDTO.getDishPrice())
                .category(CategoryEntity.builder()
                        .categoryId(categoryService.getCategoryIdByName(dishDTO.getCategory()))
                        .categoryName(dishDTO.getCategory()).build())
                .kitchenType(KitchenTypeEntity.builder()
                        .kitchenTypeId(kitchenTypeService.getKitchenTypeIdByName(dishDTO.getKitchenType()))
                        .kitchenTypeName(dishDTO.getKitchenType()).build())
                .build();
    }
}
