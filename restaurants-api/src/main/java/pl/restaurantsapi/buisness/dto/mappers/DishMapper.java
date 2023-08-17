package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.DishDTO;
import pl.restaurantsapi.infrastructure.database.entities.DishEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {

    public DishDTO map(DishEntity dishEntity);

    public DishEntity map(DishDTO dishDTO);
}
