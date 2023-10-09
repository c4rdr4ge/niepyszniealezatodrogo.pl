package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.UserDTO;
import pl.webapp.infrastructure.database.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDTO map(UserEntity userEntity);

    UserEntity map(UserDTO userDTO);
}
