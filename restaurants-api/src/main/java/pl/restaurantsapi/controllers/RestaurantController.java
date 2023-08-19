package pl.restaurantsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.RestaurantDTO;
import pl.restaurantsapi.buisness.services.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantController {

    RestaurantService restaurantService;

    @GetMapping(value = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping(value = "/restaurant-by-id/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantDTO getRestaurantById(@PathVariable Integer restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @GetMapping(value = "/restaurant-by-kitchen-type/{kitchenTypeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> getRestaurantsByKitchenType(@PathVariable String kitchenTypeName){
        return restaurantService.getRestaurantsByKitchenTypeName(kitchenTypeName);
    }

    @GetMapping(value = "/restaurant-by-name/{restaurantName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> getRestaurantsByName(@PathVariable String restaurantName){
        return restaurantService.getRestaurantsByRestaurantName(restaurantName);
    }

    @PostMapping(value = "/restaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantDTO> addNewRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        restaurantService.addNewRestaurant(restaurantDTO);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.CREATED);
    }


}
