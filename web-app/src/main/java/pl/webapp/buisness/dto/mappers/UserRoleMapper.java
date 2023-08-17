package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.UserRoleDTO;
import pl.webapp.infrastructure.database.entity.UserRoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {

    public UserRoleDTO map(UserRoleEntity userRoleEntity);

    public UserRoleEntity map(UserRoleDTO userRoleDTO);
}
