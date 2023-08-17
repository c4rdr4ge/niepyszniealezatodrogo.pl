package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.MenuPositionDTO;
import pl.restaurantsapi.infrastructure.database.entities.MenuPositionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuPositionMapper {

    public MenuPositionDTO map(MenuPositionEntity menuPositionEntity);

    public MenuPositionEntity map(MenuPositionDTO menuPositionDTO);
}
