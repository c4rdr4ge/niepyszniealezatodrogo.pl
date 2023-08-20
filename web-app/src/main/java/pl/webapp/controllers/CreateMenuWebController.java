package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateMenuWebController {

    @GetMapping(value = "/create-menu")
    public String getCreateMenuPage() {
        return "create_menu";
    }
}
