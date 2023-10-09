package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderDetailsWebController {

    @GetMapping(value = "/order-details")
    public String getOrderDetailsPage() {
        return "order_details";
    }
}
