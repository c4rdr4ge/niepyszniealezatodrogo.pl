package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.MenuDTO;
import pl.restaurantsapi.buisness.services.MenuService;
import pl.restaurantsapi.infrastructure.database.entities.MenuEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper {

    MenuDTO map(MenuEntity menuEntity);

    MenuEntity map(MenuDTO menuDTO);

}
