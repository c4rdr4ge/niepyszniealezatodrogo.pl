package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowOrdersWebController {

    @GetMapping(value = "/show-orders")
    public String getShowOrdersPage() {
        return "show_orders";
    }
}
