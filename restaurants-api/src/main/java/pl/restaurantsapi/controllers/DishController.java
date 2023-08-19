package pl.restaurantsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.DishDTO;
import pl.restaurantsapi.buisness.services.DishService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DishController {

    DishService dishService;

    @PostMapping(value = "/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishDTO> addNewDish(@RequestBody DishDTO dishDTO) {
        dishService.addNewDish(dishDTO);
        return new ResponseEntity<>(dishDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/dish", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishDTO> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping(value = "/dish-by-category/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishDTO> getDishesByCategoryName(@PathVariable String categoryName) {
        return dishService.getDishesByCategoryName(categoryName);
    }

    @GetMapping(value = "/dish-by-kitchen-type/{kitchenType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishDTO> getDishesByKitchenType(@PathVariable String kitchenType) {
        return dishService.getDishesByKitchenTypeName(kitchenType);
    }

}
