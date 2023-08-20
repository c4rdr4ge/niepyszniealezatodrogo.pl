package pl.webapp.controllers.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerPanelWebController {

    @GetMapping(value = "customer-panel")
    public String getCustomerPanelPage() {
        return "customer_panel";
    }
}
