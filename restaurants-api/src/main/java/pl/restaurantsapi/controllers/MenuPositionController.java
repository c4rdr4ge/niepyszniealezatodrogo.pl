package pl.restaurantsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.MenuPositionDTO;
import pl.restaurantsapi.buisness.services.MenuPositionService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuPositionController {

    MenuPositionService menuPositionService;

    @GetMapping(value = "/menu-position-by-id/{menuPositionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuPositionDTO getMenuPositionById(@PathVariable Integer menuPositionId){
        return menuPositionService.getMenuPositionById(menuPositionId);
    }

    @GetMapping(value = "/menu-position", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuPositionDTO> getAllMenuPositions(){
        return menuPositionService.getAllMenuPositions();
    }

    @GetMapping(value = "/menu-position-by-menu-id/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuPositionDTO> getMenuPositionsByMenuId(@PathVariable Integer menuId) {
        return menuPositionService.getMenuPositionsByMenuId(menuId);
    }

    @PostMapping(value = "/menu-position", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuPositionDTO> addNewMenuPosition(@RequestBody MenuPositionDTO menuPositionDTO){
        menuPositionService.addNewMenuPosition(menuPositionDTO);
        return new ResponseEntity<>(menuPositionDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/menu-position", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuPositionDTO> deleteMenuPosition(@RequestBody MenuPositionDTO menuPositionDTO){
        menuPositionService.deleteMenuPosition(menuPositionDTO);
        return new ResponseEntity<>(menuPositionDTO, HttpStatus.NO_CONTENT);
    }
}
