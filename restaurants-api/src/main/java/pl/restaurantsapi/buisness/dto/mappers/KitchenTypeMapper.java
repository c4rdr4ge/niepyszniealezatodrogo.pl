package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.KitchenTypeDTO;
import pl.restaurantsapi.infrastructure.database.entities.KitchenTypeEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KitchenTypeMapper {

    public KitchenTypeDTO map(KitchenTypeEntity kitchenType);

    public KitchenTypeEntity map(KitchenTypeDTO kitchenTypeDTO);
}
