package pl.webapp.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.webapp.buisness.dto.OrderDTO;
import pl.webapp.buisness.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRESTController {

    OrderService orderService;

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/undelivered-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getUndeliveredOrders() {
        return orderService.getUndeliveredOrders();
    }

    @GetMapping(value = "/orders-by-restaurant-id/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrdersByRestaurantId(@PathVariable Integer restaurantId) {
        return orderService.getOrdersByRestaurantId(restaurantId);
    }

    @PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> addNewOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addNewOrder(orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

}
