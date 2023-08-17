package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.MenuDTO;
import pl.restaurantsapi.buisness.dto.mappers.KitchenTypeMapper;
import pl.restaurantsapi.buisness.dto.mappers.MenuMapper;
import pl.restaurantsapi.infrastructure.database.entities.MenuEntity;
import pl.restaurantsapi.infrastructure.database.repositories.KitchenTypeRepository;
import pl.restaurantsapi.infrastructure.database.repositories.MenuRepository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuService {

    MenuRepository menuRepository;
    MenuMapper menuMapper;

    KitchenTypeRepository kitchenTypeRepository;
    KitchenTypeMapper kitchenTypeMapper;

    @Transactional
    public MenuDTO getMenuById(Integer menuId) {
        Optional<MenuEntity> menuEntity = menuRepository.findById(menuId);
        if (menuEntity.isPresent()) {
            return menuMapper.map(menuEntity.get());
        }else {
            throw new EntityNotFoundException("Menu with menuId: [%s], not found.".formatted(menuId));
        }
    }

    @Transactional
    public List<MenuDTO> getMenuByKitchenTypeName(String kitchenTypeName) {
        return menuRepository.findAll().stream()
                .filter(menu -> kitchenTypeName.equals(menu.getKitchenType().getKitchenTypeName()))
                .map(menu -> menuMapper.map(menu))
                .toList();
    }

    @Transactional
    public void addNewMenu(MenuDTO menuDTO) {
        MenuEntity newMenu = MenuEntity.builder()
                .kitchenType(kitchenTypeMapper.map(menuDTO.getKitchenType()))
                .build();

        menuRepository.save(newMenu);
    }
}
