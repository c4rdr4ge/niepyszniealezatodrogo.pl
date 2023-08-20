package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeWebController {

    @GetMapping(value = "/")
    public String homePage() {
        return "home";
    }
}
