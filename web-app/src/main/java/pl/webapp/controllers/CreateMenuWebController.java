package pl.webapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.webapp.buisness.dto.webdtos.WebDishDTO;
import pl.webapp.buisness.services.UserService;
import pl.webapp.consumer.restaurant_api_dtos.RESTDishDTO;
import pl.webapp.consumer.services.MenuConsumerService;
import pl.webapp.consumer.services.RestaurantConsumerService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreateMenuWebController {

    MenuConsumerService menuConsumerService;
    RestaurantConsumerService restaurantConsumerService;

    UserService userService;

    public static String UPLOAD_DIR = System.getProperty("user.dir") + "/web-app/src/main/resources/static/images/";

    @GetMapping(value = "/create-menu")
    public String getCreateMenuPage(
            @RequestParam(value = "restaurantId") Integer restaurantId,
            @RequestParam(value = "restaurantName", required = false) String restaurantName,
            @ModelAttribute(value = "webDishDTO") WebDishDTO webDishDTO,
            Model model
    ){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<RESTDishDTO> menuPositions = menuConsumerService.getDishesInRestaurant(restaurantId);
        model.addAttribute("username", username);
        model.addAttribute("menuPositions", menuPositions);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("restaurantName", restaurantName);
        return "create_menu";
    }

    @PostMapping(value = "/create-menu/upload")
    public String createNewMenuPosition(
            @RequestParam(name = "restaurantId") Integer restaurantId,
            @RequestParam(name = "restaurantName") String restaurantName,
            @ModelAttribute(value = "webDishDTO") WebDishDTO webDishDTO,
            Model model
    ) throws IOException {
        System.out.println(UPLOAD_DIR);
        Files.write(Path.of(UPLOAD_DIR + webDishDTO.getDishPhotoFile().getOriginalFilename()), webDishDTO.getDishPhotoFile().getBytes());
        menuConsumerService.saveDishToMenu(webDishDTO);
        return "redirect:/create-menu?restaurantId=" + restaurantId +"&restaurantName=" + restaurantName;
    }
}
