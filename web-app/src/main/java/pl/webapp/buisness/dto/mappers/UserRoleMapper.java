package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.UserRoleDTO;
import pl.webapp.infrastructure.database.entity.UserRoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {

    UserRoleDTO map(UserRoleEntity userRoleEntity);

    UserRoleEntity map(UserRoleDTO userRoleDTO);
}
