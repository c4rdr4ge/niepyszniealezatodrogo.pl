package pl.restaurantsapi.buisness.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.AddressDTO;
import pl.restaurantsapi.buisness.dto.AvailableAddressDTO;
import pl.restaurantsapi.buisness.dto.RestaurantDTO;
import pl.restaurantsapi.buisness.dto.mappers.AddressMapper;
import pl.restaurantsapi.buisness.dto.mappers.AvailableAddressMapper;
import pl.restaurantsapi.buisness.dto.mappers.RestaurantMapper;
import pl.restaurantsapi.infrastructure.database.entities.AvailableAddressEntity;
import pl.restaurantsapi.infrastructure.database.repositories.AvailableAddressRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AvailableAddressService {

    AvailableAddressRepository availableAddressRepository;
    AvailableAddressMapper availableAddressMapper;
    AddressMapper addressMapper;
    RestaurantMapper restaurantMapper;

    @Transactional
    public void addAvailableAddress(AvailableAddressDTO availableAddressDTO) {
        AvailableAddressEntity availableAddress = AvailableAddressEntity.builder()
                .address(addressMapper.map(availableAddressDTO.getAddress()))
                .restaurant(restaurantMapper.map(availableAddressDTO.getRestaurant()))
                .build();
        availableAddressRepository.save(availableAddress);
    }

    @Transactional
    public List<AddressDTO> getAvailableAddressesByRestaurantId(Integer restaurantId) {
        return availableAddressRepository.findAll().stream()
                .filter(address -> restaurantId.equals(address.getRestaurant().getRestaurantId()))
                .map(address -> addressMapper.map(address.getAddress()))
                .toList();
    }

    @Transactional
    public List<RestaurantDTO> getAvailableRestaurantsByStreet(String street) {
        return availableAddressRepository.findAll().stream()
                .filter(address -> street.equals(address.getAddress().getAddressStreet()))
                .map(address -> restaurantMapper.map(address.getRestaurant()))
                .toList();
    }
}
