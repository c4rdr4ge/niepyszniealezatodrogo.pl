package pl.webapp.consumer.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.consumer.restaurant_api_dtos.RESTRestaurantDTO;
import pl.webapp.consumer.utils.URLS;

import java.util.Objects;

@Service
public class RestaurantConsumerService {

    public String getRestaurantNameFromApi(Integer restaurantId) {
        String url = URLS.RESTAURANT_BY_ID_URL + restaurantId;

        WebClient.Builder builder = WebClient.builder();
        RESTRestaurantDTO body = builder.build().get().uri(url).retrieve().bodyToMono(RESTRestaurantDTO.class).block();

        return Objects.requireNonNull(body).getRestaurantName();
    }
}
