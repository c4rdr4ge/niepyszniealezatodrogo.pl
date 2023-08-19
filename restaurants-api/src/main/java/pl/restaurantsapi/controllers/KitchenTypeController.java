package pl.restaurantsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.KitchenTypeDTO;
import pl.restaurantsapi.buisness.services.KitchenTypeService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class KitchenTypeController {

    KitchenTypeService kitchenTypeService;

    @GetMapping(value = "/kitchen-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KitchenTypeDTO> getAllKitchenTypes(){
        return kitchenTypeService.getAllKitchenTypes();
    }

    @GetMapping(value = "/kitchen-type-by-id/{kitchenTypeId}")
    public KitchenTypeDTO getKitchenTypeById(@PathVariable Integer kitchenTypeId){
        return kitchenTypeService.getKitchenTypeById(kitchenTypeId);
    }

    @PostMapping(value = "/kitchen-type", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KitchenTypeDTO> addNewKitchenType(@RequestBody KitchenTypeDTO kitchenTypeDTO){
        kitchenTypeService.addNewKitchenType(kitchenTypeDTO);
        return new ResponseEntity<>(kitchenTypeDTO, HttpStatus.CREATED);
    }
}
