package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.MenuPositionDTO;
import pl.restaurantsapi.buisness.dto.mappers.DishMapper;
import pl.restaurantsapi.buisness.dto.mappers.MenuMapper;
import pl.restaurantsapi.buisness.dto.mappers.MenuPositionMapper;
import pl.restaurantsapi.infrastructure.database.entities.MenuPositionEntity;
import pl.restaurantsapi.infrastructure.database.repositories.DishRepository;
import pl.restaurantsapi.infrastructure.database.repositories.MenuPositionRepository;
import pl.restaurantsapi.infrastructure.database.repositories.MenuRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuPositionService {

    MenuPositionRepository menuPositionRepository;

    MenuRepository menuRepository;
    MenuPositionMapper menuPositionMapper;

    CategoryService categoryService;
    KitchenTypeService kitchenTypeService;

    MenuMapper menuMapper;
    DishMapper dishMapper;

    DishRepository dishRepository;


    @Transactional
    public MenuPositionDTO getMenuPositionById(Integer menuPositionId) {
        Optional<MenuPositionEntity> menuPositionEntity = menuPositionRepository.findById(menuPositionId);
        if (menuPositionEntity.isPresent()) {
            return menuPositionMapper.map(menuPositionEntity.get());
        }else {
            throw new EntityNotFoundException("MenuPosition with menuPositionId: [%s], not found.". formatted(menuPositionId));
        }
    }

    @Transactional
    public List<MenuPositionDTO> getAllMenuPositions() {
        return menuPositionRepository.findAll().stream()
                .map(menuPosition -> menuPositionMapper.map(menuPosition))
                .toList();
    }

    @Transactional
    public List<MenuPositionDTO> getMenuPositionsByMenuId(Integer menuId) {
        return menuPositionRepository.findAll().stream()
                .filter(menuPosition -> menuId.equals(menuPosition.getMenu().getMenuId()))
                .map(menuPosition -> menuPositionMapper.map(menuPosition))
                .toList();
    }

    @Transactional
    public MenuPositionEntity getMenuPositionByDishId(Integer dishId) {
        return menuPositionRepository.findAll().stream()
                .filter(menuPosition -> dishId.equals(menuPosition.getDish().getDishId()))
                .findAny().orElseThrow(() -> new RuntimeException("There is no dish with id: [%s]".formatted(dishId)));
    }

    @Transactional
    public void addNewMenuPosition(MenuPositionDTO menuPositionDTO) {
        MenuPositionEntity newMenuPosition = MenuPositionEntity.builder()
                .menu(menuRepository.findById(menuPositionDTO.getMenu().getMenuId())
                        .orElseThrow(() -> new RuntimeException("There is no menu with id: [%s]".formatted(menuPositionDTO.getMenu().getMenuId()))))
                .dish(dishRepository.findById(menuPositionDTO.getDish().getDishId())
                        .orElseThrow(() -> new RuntimeException("There is no dish with id: [%s]".formatted(menuPositionDTO.getDish().getDishId()))))
                .build();

        menuPositionRepository.save(newMenuPosition);
    }

    @Transactional
    public void deleteMenuPosition(MenuPositionDTO menuPositionDTO) {
        MenuPositionEntity menuPositionEntity = getMenuPositionByDishId(menuPositionDTO.getDish().getDishId());

        menuPositionRepository.delete(menuPositionEntity);
    }

}
