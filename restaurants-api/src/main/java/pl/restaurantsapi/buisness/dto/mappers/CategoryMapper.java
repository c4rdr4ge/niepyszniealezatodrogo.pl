package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.CategoryDTO;
import pl.restaurantsapi.infrastructure.database.entities.CategoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    public CategoryDTO map(CategoryEntity categoryEntity);

    public CategoryEntity map(CategoryDTO categoryDTO);
}
