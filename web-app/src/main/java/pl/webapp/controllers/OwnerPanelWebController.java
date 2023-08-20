package pl.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerPanelWebController {

    @GetMapping(value = "owner-panel")
    public String getOwnerPanelPage() {
        return "owner_panel";
    }
}
