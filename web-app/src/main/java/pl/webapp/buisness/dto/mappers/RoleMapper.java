package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.RoleDTO;
import pl.webapp.infrastructure.database.entity.RoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleDTO map(RoleEntity roleEntity);

    RoleEntity map(RoleDTO roleDTO);
}
