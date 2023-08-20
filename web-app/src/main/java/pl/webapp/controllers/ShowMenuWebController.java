package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowMenuWebController {

    @GetMapping(value = "/show-menu")
    public String getShowMenuPage() {
        return "show_menu";
    }
}
