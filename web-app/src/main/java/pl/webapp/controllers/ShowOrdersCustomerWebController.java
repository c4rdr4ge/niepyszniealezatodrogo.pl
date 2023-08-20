package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowOrdersCustomerWebController {

    @GetMapping(value = "show-orders-customer")
    public String getShowOrdersCustomerPage() {
        return "show_orders_customer";
    }
}
