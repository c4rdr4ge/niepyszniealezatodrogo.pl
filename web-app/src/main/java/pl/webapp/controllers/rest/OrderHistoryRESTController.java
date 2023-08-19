package pl.webapp.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.webapp.buisness.dto.OrderHistoryDTO;
import pl.webapp.buisness.services.OrderHistoryService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderHistoryRESTController {

    OrderHistoryService orderHistoryService;

    @GetMapping(value = "/order-history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderHistoryDTO> getAllOrderHistory() {
        return orderHistoryService.getAllOrderHistory();
    }

    @GetMapping(value = "/order-history-by-username/{username}")
    public List<OrderHistoryDTO> getOrderHistoryByUserName(@PathVariable String username) {
        return orderHistoryService.getOrderHistoryByUserName(username);
    }

    @PostMapping(value = "/order-history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderHistoryDTO> addNewOrderHistory(@RequestBody OrderHistoryDTO orderHistoryDTO) {
        orderHistoryService.addNewOrderHistory(orderHistoryDTO);
        return new ResponseEntity<>(orderHistoryDTO, HttpStatus.CREATED);
    }
}
