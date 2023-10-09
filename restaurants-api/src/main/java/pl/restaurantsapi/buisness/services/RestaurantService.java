package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.RestaurantDTO;
import pl.restaurantsapi.buisness.dto.mappers.AddressMapper;
import pl.restaurantsapi.buisness.dto.mappers.KitchenTypeMapper;
import pl.restaurantsapi.buisness.dto.mappers.MenuMapper;
import pl.restaurantsapi.buisness.dto.mappers.RestaurantMapper;
import pl.restaurantsapi.infrastructure.database.entities.RestaurantEntity;
import pl.restaurantsapi.infrastructure.database.repositories.KitchenTypeRepository;
import pl.restaurantsapi.infrastructure.database.repositories.RestaurantRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantService {

    RestaurantRepository restaurantRepository;
    RestaurantMapper restaurantMapper;

    KitchenTypeMapper kitchenTypeMapper;
    AddressMapper addressMapper;
    MenuMapper menuMapper;

    @Transactional
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> restaurantMapper.map(restaurant))
                .toList();
    }

    @Transactional
    public RestaurantDTO getRestaurantById(Integer restaurantId) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(restaurantId);
        if (restaurantEntity.isPresent()) {
            return restaurantMapper.map(restaurantEntity.get());
        }else {
            throw new EntityNotFoundException("Restaurant with restaurantId: [%s], not found.".formatted(restaurantId));
        }
    }

    @Transactional
    public List<RestaurantDTO> getRestaurantsByKitchenTypeName(String kitchenTypeName) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> kitchenTypeName.equals(restaurant.getKitchenType().getKitchenTypeName()))
                .map(restaurant -> restaurantMapper.map(restaurant))
                .toList();
    }

    @Transactional
    public List<RestaurantDTO> getRestaurantsByRestaurantName(String restaurantName) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurantName.equals(restaurant.getRestaurantName()))
                .map(restaurant -> restaurantMapper.map(restaurant))
                .toList();
    }

    @Transactional
    public void addNewRestaurant(RestaurantDTO restaurantDTO) {
        RestaurantEntity newRestaurant = RestaurantEntity.builder()
                .restaurantName(restaurantDTO.getRestaurantName())
                .restaurantPhone(restaurantDTO.getRestaurantPhone())
                .restaurantRating(new BigDecimal("0.0"))
                .kitchenType(kitchenTypeMapper.map(restaurantDTO.getKitchenType()))
                .address(addressMapper.map(restaurantDTO.getAddress()))
                .menu(menuMapper.map(restaurantDTO.getMenu()))
                .build();

        restaurantRepository.save(newRestaurant);
    }


    public List<RestaurantDTO> getRestaurantByOwnerId(Integer ownerId) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> ownerId.equals(restaurant.getRestaurantOwner().getRestaurantOwnerId()))
                .map(restaurant ->restaurantMapper.map(restaurant))
                .toList();
    }
}
