package pl.webapp.consumer.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.consumer.restaurant_api_dtos.RESTDishDTO;
import pl.webapp.consumer.restaurant_api_dtos.RESTRestaurantDTO;
import pl.webapp.consumer.utils.URLS;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantConsumerService {

    public String getRestaurantNameFromApi(Integer restaurantId) {
        String url = URLS.RESTAURANT_BY_ID_URL + restaurantId;

        WebClient.Builder builder = WebClient.builder();
        RESTRestaurantDTO body = builder.build().get().uri(url).retrieve().bodyToMono(RESTRestaurantDTO.class).block();

        return Objects.requireNonNull(body).getRestaurantName();
    }

    public List<RESTRestaurantDTO> getRestaurantsByOwnerIdFromApi(Integer ownerId) {
        String url = URLS.RESTAURANT_BY_OWNER_ID_URL + ownerId;

        WebClient.Builder builder = WebClient.builder();
        List<RESTRestaurantDTO> restaurantDTOS = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RESTRestaurantDTO>>() {})
                .block();

        return restaurantDTOS;
    }
}
