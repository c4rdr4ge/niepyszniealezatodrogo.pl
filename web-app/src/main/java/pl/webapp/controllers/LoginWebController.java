package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginWebController {

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login_panel";
    }
}
