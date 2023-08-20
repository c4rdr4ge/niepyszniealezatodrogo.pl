package pl.webapp.consumer.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.consumer.restaurant_api_dtos.RESTAddressDTO;
import pl.webapp.consumer.utils.URLS;

import java.util.Objects;

@Service
public class AddressConsumerService {


    public String getAddressNameFromApi(Integer addressId) {
        String url = URLS.ADDRESS_ID_URL + addressId;

        WebClient.Builder builder = WebClient.builder();
        RESTAddressDTO body = builder.build().get().uri(url).retrieve().bodyToMono(RESTAddressDTO.class).block();

        return Objects.requireNonNull(body).getAddressStreet();
    }
}
