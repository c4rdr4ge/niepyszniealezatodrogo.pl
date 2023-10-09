package pl.webapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.webapp.buisness.dto.OrderDTO;
import pl.webapp.buisness.dto.mappers.OrderMapper;
import pl.webapp.buisness.dto.webdtos.WebCourierAddressDTO;
import pl.webapp.buisness.services.OrderService;
import pl.webapp.consumer.services.AddressConsumerService;
import pl.webapp.consumer.services.RestaurantConsumerService;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CourierPanelWebController {

    OrderService orderService;
    OrderMapper orderMapper;
    AddressConsumerService addressConsumerService;
    RestaurantConsumerService restaurantConsumerService;

    @GetMapping(value = "/courier-panel")
    public String courierPanelPage(Model model) {
        Stream<WebCourierAddressDTO> undeliveredOrders = orderService.getUndeliveredOrders().stream()
                .map(orderDTO -> WebCourierAddressDTO.builder()
                        .orderRestaurantName(restaurantConsumerService.getRestaurantNameFromApi(orderDTO.getOrderRestaurantId()))
                        .orderNumber(orderDTO.getOrderNumber())
                        .orderAddressStreet(addressConsumerService.getAddressNameFromApi(orderDTO.getOrderAddressId()))
                        .orderDateTime(orderDTO.getOrderDateTime())
                        .build());


        model.addAttribute("orders", undeliveredOrders);
        return "courier_panel";
    }

    @PutMapping(value = "/courier-panel/{orderNumber}")
    public String deliverOrder (@PathVariable String orderNumber) {
        OrderDTO orderByOrderNumber = orderService.getOrderByOrderNumber(orderNumber);
        orderByOrderNumber.setOrderDelivered(true);
        orderByOrderNumber.setOrderDeliveredDateTime(OffsetDateTime.now());

        orderService.updateOrder(orderByOrderNumber);

        return "redirect:/courier-panel";
    }
}
