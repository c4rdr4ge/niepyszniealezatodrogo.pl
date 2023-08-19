package pl.restaurantsapi.buisness.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.DishDTO;
import pl.restaurantsapi.buisness.dto.mappers.CategoryMapper;
import pl.restaurantsapi.buisness.dto.mappers.DishMapper;
import pl.restaurantsapi.buisness.dto.mappers.KitchenTypeMapper;
import pl.restaurantsapi.infrastructure.database.entities.DishEntity;
import pl.restaurantsapi.infrastructure.database.repositories.DishRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DishService {

    DishRepository dishRepository;
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
    public void addNewDish(DishDTO dishDTO) {
        DishEntity dishEntity = DishEntity.builder()
                .dishName(dishDTO.getDishName())
                .dishDescription(dishDTO.getDishDescription())
                .dishWeight(dishDTO.getDishWeight())
                .dishPhotoUrl(dishDTO.getDishPhotoUrl())
                .dishPrice(dishDTO.getDishPrice())
                .category(categoryMapper.map(dishDTO.getCategory()))
                .kitchenType(kitchenTypeMapper.map(dishDTO.getKitchenType()))
                .build();

        dishRepository.save(dishEntity);
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
