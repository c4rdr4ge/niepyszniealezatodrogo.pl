package pl.webapp.consumer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.buisness.dto.DishDTO;
import pl.webapp.buisness.dto.MenuPositionDTO;
import pl.webapp.buisness.dto.webdtos.WebDishDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTDishDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTMenuDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTMenuPositionDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTRestaurantDTO;
import pl.webapp.consumer.utils.URLS;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuConsumerService {

    RestaurantConsumerService restaurantConsumerService;
    private WebClient.Builder webClient;

    public RESTRestaurantDTO getRestaurantByRestaurantId(Integer restaurantId) {
        String url = URLS.RESTAURANT_BY_ID_URL + restaurantId;

        WebClient.Builder builder = WebClient.builder();
        RESTRestaurantDTO restaurant = builder.build().get().uri(url).retrieve().bodyToMono(RESTRestaurantDTO.class).block();

        return Objects.requireNonNull(restaurant);
    }

    public List<RESTDishDTO> getDishesByRestaurantId(Integer restaurantId) {
        Integer menuId = getRestaurantByRestaurantId(restaurantId).getMenu().getMenuId();
        String url = URLS.MENU_POSITION_BY_MENU_ID_URL + menuId;

        WebClient.Builder builder = WebClient.builder();
        List<RESTMenuPositionDTO> positions = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RESTMenuPositionDTO>>() {})
                .block();

        return positions.stream()
                .map(restMenuPositionDTO -> restMenuPositionDTO.getDish())
                .toList();
    }

    public DishDTO saveDishToMenu(WebDishDTO webDishDTO) {
        String url = URLS.DISH_URL;

        DishDTO dishDTO = DishDTO.builder()
                .dishName(webDishDTO.getDishName())
                .dishDescription(webDishDTO.getDishDescription())
                .dishWeight(webDishDTO.getDishWeight())
                .dishPhotoUrl("/images/" + webDishDTO.getDishPhotoFile().getOriginalFilename())
                .dishPrice(webDishDTO.getDishPrice())
                .category(webDishDTO.getCategory())
                .kitchenType(webDishDTO.getKitchenType())
                .build();

        DishDTO block = webClient
                .build()
                .post()
                .uri(url)
                .bodyValue(dishDTO)
                .retrieve()
                .bodyToMono(DishDTO.class)
                .block();

        return block;
    }

    public MenuPositionDTO saveMenuPosition(Integer restaurantId, WebDishDTO webDishDTO) {

        String url = URLS.MENU_POSITION_URL;

        DishDTO dishDTO = saveDishToMenu(webDishDTO);

        MenuPositionDTO menuPositionDTO = MenuPositionDTO
                .builder()
                .menu(getRestaurantByRestaurantId(restaurantId).getMenu())
                .dish(dishDTO)
                .build();

        MenuPositionDTO block = webClient
                .build()
                .post()
                .uri(url)
                .bodyValue(menuPositionDTO)
                .retrieve()
                .bodyToMono(MenuPositionDTO.class)
                .block();

        return block;
    }

    public MenuPositionDTO deleteMenuPosition(Integer dishId) {

        String url = URLS.MENU_POSITION_URL;


        List<MenuPositionDTO> block1 = webClient.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<MenuPositionDTO>>() {
                })
                .block();

        MenuPositionDTO menuPositionDTO1 = block1.stream().filter(menuPositionDTO -> dishId.equals(menuPositionDTO.getDish().getDishId()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("There is no menu position with dish with id: [%s]".formatted(dishId)));

        MenuPositionDTO block2 = webClient.build()
                .method(HttpMethod.DELETE)
                .uri(url)
                .bodyValue(menuPositionDTO1)
                .retrieve()
                .bodyToMono(MenuPositionDTO.class)
                .block();

        return block2;
    }
}
