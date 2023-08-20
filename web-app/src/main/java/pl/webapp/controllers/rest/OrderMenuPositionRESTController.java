package pl.webapp.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.webapp.buisness.dto.OrderMenuPositionDTO;
import pl.webapp.buisness.services.OrderMenuPositionService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderMenuPositionRESTController {

    OrderMenuPositionService orderMenuPositionService;

    @GetMapping(value = "/order-menu-position", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderMenuPositionDTO> getAllOrderMenuPositions() {
        return orderMenuPositionService.getAllOrderMenuPosition();
    }

    @GetMapping(value = "/order-menu-position-by-menu-position-id/{menuPositionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderMenuPositionDTO getOrderMenuPositionByMenuPositionId(@PathVariable Integer menuPositionId) {
        return orderMenuPositionService.getOrderMenuPositionByMenuPositionId(menuPositionId);
    }

    @PostMapping(value = "/order-menu-position", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderMenuPositionDTO> addNewOrderMenuPosition(@RequestBody OrderMenuPositionDTO orderMenuPositionDTO) {
        orderMenuPositionService.addOrderMenuPosition(orderMenuPositionDTO);
        return new ResponseEntity<>(orderMenuPositionDTO, HttpStatus.CREATED);
    }

}
