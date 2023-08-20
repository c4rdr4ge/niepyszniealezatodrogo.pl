package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccCreatorController {

    @GetMapping(value = "/signup")
    public String signupPage() {
        return "acc_creator";
    }
}
