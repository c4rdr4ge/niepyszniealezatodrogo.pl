package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderHistoryWebController {

    @GetMapping(value = "order-history")
    public String getOrderHistoryPage() {
        return "order_history";
    }
}
