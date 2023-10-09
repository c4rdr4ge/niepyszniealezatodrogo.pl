package pl.webapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class RedirectorWebController {

    @GetMapping(value = "/redirector")
    public String redirect() {
        Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();
        if ("ROLE_OWNER".equals(myRole)){
            return "redirect:/owner-panel";
        }else if ("ROLE_DELIVERY".equals(myRole)){
            return "redirect:/courier-panel";
        } else if ("ROLE_CUSTOMER".equals(myRole)) {
            return "redirect:/customer-panel";
        }
        return "redirect:/customer-panel";
    }
}
