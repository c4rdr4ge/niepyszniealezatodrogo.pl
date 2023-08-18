package pl.restaurantsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.restaurantsapi.buisness.dto.CategoryDTO;
import pl.restaurantsapi.buisness.services.CategoryService;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    CategoryService categoryService;

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> addNewCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.addNewCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }
}
