package pl.webapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.webapp.buisness.services.UserService;
import pl.webapp.consumer.restaurant_api_dtos.RESTRestaurantDTO;
import pl.webapp.consumer.services.RestaurantConsumerService;

import java.util.List;

@Controller
@AllArgsConstructor
public class OwnerPanelWebController {

    RestaurantConsumerService restaurantConsumerService;
    UserService userService;

    @GetMapping(value = "owner-panel")
    public String getOwnerPanelPage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<RESTRestaurantDTO> restaurantsByOwnerIdFromApi = restaurantConsumerService.getRestaurantsByOwnerIdFromApi(userService.getUserByUserName(username).getUserId());
        model.addAttribute("username", username);
        model.addAttribute("restaurants", restaurantsByOwnerIdFromApi);
        return "owner_panel";
    }
}
