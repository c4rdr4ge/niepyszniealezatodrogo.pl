package pl.restaurantsapi.controllers;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.RestaurantOwnerDTO;
import pl.restaurantsapi.buisness.services.RestaurantOwnerService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantOwnerController {

    RestaurantOwnerService restaurantOwnerService;

    @GetMapping(value = "/restaurant-owner", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantOwnerDTO> getAllRestaurantOwners(){
        return restaurantOwnerService.getAllRestaurantOwners();
    }

    @GetMapping(value = "/restaurant-owner-by-email/{restaurantOwnerEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantOwnerDTO getRestaurantOwnerByEmail(@Email @PathVariable String email){
        return restaurantOwnerService.getRestaurantOwnerByEmail(email);
    }

    @GetMapping(value = "/restaurant-owner-by-nip/{nip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantOwnerDTO getRestaurantOwnerByNip(@PathVariable String nip) {
        return restaurantOwnerService.getRestaurantOwnerByNip(nip);
    }

    @PostMapping(value = "/restaurant-owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantOwnerDTO> addNewRestaurantOwner(@RequestBody RestaurantOwnerDTO restaurantOwnerDTO){
        restaurantOwnerService.addNewRestaurantOwner(restaurantOwnerDTO);
        return new ResponseEntity<>(restaurantOwnerDTO, HttpStatus.CREATED);
    }
}
