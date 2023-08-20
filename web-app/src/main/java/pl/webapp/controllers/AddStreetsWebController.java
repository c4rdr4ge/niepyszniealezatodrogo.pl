package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddStreetsWebController {

    @GetMapping(value = "/add-street")
    public String addingStreetsPage () {
        return "add_streets";
    }
}
