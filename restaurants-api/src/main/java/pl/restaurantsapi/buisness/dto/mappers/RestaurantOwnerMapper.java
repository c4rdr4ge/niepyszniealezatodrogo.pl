package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.RestaurantOwnerDTO;
import pl.restaurantsapi.infrastructure.database.entities.RestaurantOwnerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantOwnerMapper {

    public RestaurantOwnerDTO map(RestaurantOwnerEntity restaurantOwnerEntity);

    public RestaurantOwnerEntity map(RestaurantOwnerDTO restaurantOwnerDTO);
}
