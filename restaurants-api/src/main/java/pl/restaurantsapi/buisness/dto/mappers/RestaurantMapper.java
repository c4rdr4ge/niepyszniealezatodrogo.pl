package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.RestaurantDTO;
import pl.restaurantsapi.infrastructure.database.entities.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    public RestaurantDTO map(RestaurantEntity restaurantEntity);

    public RestaurantEntity map(RestaurantDTO restaurantDTO);
}
