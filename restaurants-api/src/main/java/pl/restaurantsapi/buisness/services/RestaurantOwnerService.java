package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.RestaurantOwnerDTO;
import pl.restaurantsapi.buisness.dto.mappers.AddressMapper;
import pl.restaurantsapi.buisness.dto.mappers.RestaurantOwnerMapper;
import pl.restaurantsapi.infrastructure.database.entities.RestaurantOwnerEntity;
import pl.restaurantsapi.infrastructure.database.repositories.RestaurantOwnerRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantOwnerService {

    RestaurantOwnerRepository restaurantOwnerRepository;
    RestaurantOwnerMapper restaurantOwnerMapper;

    AddressMapper addressMapper;

    @Transactional
    public List<RestaurantOwnerDTO> getAllRestaurantOwners(){
        return restaurantOwnerRepository.findAll().stream()
                .map(restaurantOwner -> restaurantOwnerMapper.map(restaurantOwner))
                .toList();
    }

    @Transactional
    public RestaurantOwnerDTO getRestaurantOwnerByEmail(String email) {
        return restaurantOwnerRepository.findAll().stream()
                .filter(restaurantOwner -> email.equals(restaurantOwner.getRestaurantOwnerEmail()))
                .map(restaurantOwner -> restaurantOwnerMapper.map(restaurantOwner))
                .findAny().orElseThrow(
                        () -> new EntityNotFoundException("RestaurantOwner with email: [%s], not found".formatted(email))
                );
    }

    @Transactional
    public RestaurantOwnerDTO getRestaurantOwnerByNip(String nip) {
        return restaurantOwnerRepository.findAll().stream()
                .filter(restaurantOwner -> nip.equals(restaurantOwner.getRestaurantOwnerNip()))
                .map(restaurantOwner -> restaurantOwnerMapper.map(restaurantOwner))
                .findAny().orElseThrow(
                        () -> new EntityNotFoundException("RestaurantOwner with nip: [%s], not found".formatted(nip))
                );
    }

    @Transactional
    public void addNewRestaurantOwner(RestaurantOwnerDTO restaurantOwnerDTO) {
        RestaurantOwnerEntity newRestaurantOwner = RestaurantOwnerEntity.builder()
                .restaurantOwnerName(restaurantOwnerDTO.getRestaurantOwnerName())
                .restaurantOwnerSurname(restaurantOwnerDTO.getRestaurantOwnerSurname())
                .restaurantOwnerPhone(restaurantOwnerDTO.getRestaurantOwnerPhone())
                .restaurantOwnerEmail(restaurantOwnerDTO.getRestaurantOwnerEmail())
                .restaurantOwnerNip(restaurantOwnerDTO.getRestaurantOwnerNip())
                .address(addressMapper.map(restaurantOwnerDTO.getAddress()))
                .build();

        restaurantOwnerRepository.save(newRestaurantOwner);
    }
}
