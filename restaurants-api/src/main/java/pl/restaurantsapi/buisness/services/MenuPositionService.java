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
import pl.restaurantsapi.infrastructure.database.repositories.MenuPositionRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuPositionService {

    MenuPositionRepository menuPositionRepository;
    MenuPositionMapper menuPositionMapper;

    MenuMapper menuMapper;
    DishMapper dishMapper;


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
                .filter(menuPosition -> menuId.equals(menuPosition.getMenuPositionId()))
                .map(menuPosition -> menuPositionMapper.map(menuPosition))
                .toList();
    }

    @Transactional
    public void addNewMenuPosition(MenuPositionDTO menuPositionDTO) {
        MenuPositionEntity newMenuPosition = MenuPositionEntity.builder()
                .menu(menuMapper.map(menuPositionDTO.getMenu()))
                .dish(dishMapper.map(menuPositionDTO.getDish()))
                .build();
        menuPositionRepository.save(newMenuPosition);
    }
}
