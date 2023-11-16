package pl.restaurantsapi.buisness.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.CategoryDTO;
import pl.restaurantsapi.buisness.dto.DishDTO;
import pl.restaurantsapi.buisness.dto.mappers.CategoryMapper;
import pl.restaurantsapi.buisness.dto.mappers.DishMapper;
import pl.restaurantsapi.buisness.dto.mappers.KitchenTypeMapper;
import pl.restaurantsapi.infrastructure.database.entities.DishEntity;
import pl.restaurantsapi.infrastructure.database.repositories.CategoryRepository;
import pl.restaurantsapi.infrastructure.database.repositories.DishRepository;
import pl.restaurantsapi.infrastructure.database.repositories.KitchenTypeRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DishService {

    DishRepository dishRepository;

    CategoryService categoryService;
    KitchenTypeService kitchenTypeService;
    DishMapper dishMapper;

    CategoryMapper categoryMapper;
    KitchenTypeMapper kitchenTypeMapper;

    @Transactional
    public List<DishDTO> getAllDishes(){
        return dishRepository.findAll().stream()
                .map(dish -> dishMapper.map(dish))
                .toList();
    }

    @Transactional
    public DishEntity addNewDish(DishDTO dishDTO) {

        Integer categoryId = categoryService.getAllCategories().stream()
                .filter(category -> category.getCategoryName().equals(dishDTO.getCategory()))
                .map(CategoryDTO::getCategoryId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Cannot find category with name: %s".formatted(dishDTO.getCategory())));

        DishEntity dishEntity = DishEntity.builder()
                .dishName(dishDTO.getDishName())
                .dishDescription(dishDTO.getDishDescription())
                .dishWeight(dishDTO.getDishWeight())
                .dishPhotoUrl(dishDTO.getDishPhotoUrl())
                .dishPrice(dishDTO.getDishPrice())
                .category(dishMapper.map(dishDTO, categoryService, kitchenTypeService).getCategory())
                .kitchenType(dishMapper.map(dishDTO, categoryService, kitchenTypeService).getKitchenType())
                .build();

        DishEntity save = dishRepository.save(dishEntity);
        return save;
    }

    @Transactional
    public List<DishDTO> getDishesByCategoryName(String category){
        return dishRepository.findAll().stream()
                .filter(dish -> category.equals(dish.getCategory().getCategoryName()))
                .map(dish -> dishMapper.map(dish))
                .toList();
    }

    @Transactional
    public List<DishDTO> getDishesByKitchenTypeName(String kitchenType){
        return dishRepository.findAll().stream()
                .filter(dish -> kitchenType.equals(dish.getKitchenType().getKitchenTypeName()))
                .map(dish -> dishMapper.map(dish))
                .toList();
    }
}
