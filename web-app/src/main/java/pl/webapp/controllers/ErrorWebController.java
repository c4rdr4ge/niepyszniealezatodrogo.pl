package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorWebController {

    @GetMapping(value = "/error")
    public String getErrorPage() {
        return "error";
    }
}
