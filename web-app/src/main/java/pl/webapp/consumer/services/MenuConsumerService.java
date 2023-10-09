package pl.webapp.consumer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.buisness.dto.webdtos.WebDishDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTDishDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTMenuPositionDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTRestaurantDTO;
import pl.webapp.consumer.utils.URLS;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MenuConsumerService {

    RestaurantConsumerService restaurantConsumerService;

    public List<RESTDishDTO> getDishesInRestaurant(Integer restaurantId) {
        String url = URLS.RESTAURANT_BY_ID_URL + restaurantId;

        WebClient.Builder builder = WebClient.builder();
        RESTRestaurantDTO restaurant = builder.build().get().uri(url).retrieve().bodyToMono(RESTRestaurantDTO.class).block();

        Integer menuId = Objects.requireNonNull(restaurant).getMenu().getMenuId();

        return getDishesByMenuId(menuId);
    }

    public List<RESTDishDTO> getDishesByMenuId(Integer menuId) {
        String url = URLS.MENU_POSITION_BY_MENU_ID_URL + menuId;

        WebClient.Builder builder = WebClient.builder();
        List<RESTMenuPositionDTO> positions = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RESTMenuPositionDTO>>() {})
                .block();

        List<RESTDishDTO> dishes = positions.stream()
                .map(RESTMenuPositionDTO::getDish)
                .toList();

        return dishes;
    }

    public void saveDishToMenu(WebDishDTO webDishDTO) {
        String url = URLS.DISH_URL;

        // Save Dish to Database
        WebClient.Builder builder = WebClient.builder();
        builder.build()
                .post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(webDishDTO), WebDishDTO.class)
                .retrieve()
                .bodyToMono(WebDishDTO.class);
    }
}
