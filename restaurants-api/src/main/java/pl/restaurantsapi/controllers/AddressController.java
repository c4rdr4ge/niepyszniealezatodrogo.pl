package pl.restaurantsapi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.restaurantsapi.buisness.dto.AddressDTO;
import pl.restaurantsapi.buisness.services.AddressService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class AddressController {

    AddressService addressService;

    @GetMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AddressDTO> allAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping(value = "/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AddressDTO addressDetailsById(@PathVariable Integer addressId) {
        return addressService.getAddressById(addressId);
    }

    @GetMapping(value = "/address/street/{streetName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AddressDTO> addressesByStreetName(@PathVariable String streetName) {
        return addressService.getAddressByAddressStreet(streetName);
    }

    @PostMapping(value = "/address", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> addNewAddress(
            @RequestBody AddressDTO addressDTO
    ) {
        addressService.addNewAddress(addressDTO);
        return new ResponseEntity<>(addressDTO, HttpStatus.CREATED);
    }
}
