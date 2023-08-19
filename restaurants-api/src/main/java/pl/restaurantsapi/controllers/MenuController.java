package pl.restaurantsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.MenuDTO;
import pl.restaurantsapi.buisness.services.MenuService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

    MenuService menuService;

    @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuDTO> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping(value = "/menu-by-id/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuDTO getMenuByMenuId(@PathVariable Integer menuId){
        return menuService.getMenuById(menuId);
    }

    @GetMapping(value = "/menu-by-kitchen-type/{kitchenTypeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuDTO> getMenusByKitchenTypeName(@PathVariable String kitchenTypeName){
        return menuService.getMenuByKitchenTypeName(kitchenTypeName);
    }

    @PostMapping(value = "/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuDTO> addNewMenu(@RequestBody MenuDTO menuDTO){
        menuService.addNewMenu(menuDTO);
        return new ResponseEntity<>(menuDTO, HttpStatus.CREATED);
    }

}
