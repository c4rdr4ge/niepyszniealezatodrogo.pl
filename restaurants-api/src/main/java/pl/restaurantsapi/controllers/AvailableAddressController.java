package pl.restaurantsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.AddressDTO;
import pl.restaurantsapi.buisness.dto.AvailableAddressDTO;
import pl.restaurantsapi.buisness.dto.RestaurantDTO;
import pl.restaurantsapi.buisness.services.AvailableAddressService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AvailableAddressController {

    AvailableAddressService availableAddressService;

    @PostMapping(value = "/available-address", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvailableAddressDTO> addNewAvailableAddress(@RequestBody AvailableAddressDTO availableAddressDTO) {
        availableAddressService.addAvailableAddress(availableAddressDTO);
        return new ResponseEntity<>(availableAddressDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/available-address/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AddressDTO> availableAddressByRestaurantId(@PathVariable Integer restaurantId) {
        return availableAddressService.getAvailableAddressesByRestaurantId(restaurantId);
    }

    @GetMapping(value = "/available-restaurant/{streetName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> availableRestaurantsByStreetName(@PathVariable String streetName) {
        return availableAddressService.getAvailableRestaurantsByStreet(streetName);
    }
}
