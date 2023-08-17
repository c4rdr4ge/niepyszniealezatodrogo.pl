package pl.webapp.buisness.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.buisness.dto.UserRoleDTO;
import pl.webapp.buisness.dto.mappers.RoleMapper;
import pl.webapp.buisness.dto.mappers.UserMapper;
import pl.webapp.buisness.dto.mappers.UserRoleMapper;
import pl.webapp.infrastructure.database.entity.UserRoleEntity;
import pl.webapp.infrastructure.database.repositories.UserRoleRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleService {

    UserRoleRepository userRoleRepository;
    UserRoleMapper userRoleMapper;

    UserMapper userMapper;
    RoleMapper roleMapper;

    @Transactional
    public void addNewUserRole(UserRoleDTO userRoleDTO) {
        UserRoleEntity newUserRole = UserRoleEntity.builder()
                .user(userMapper.map(userRoleDTO.getUser()))
                .role(roleMapper.map(userRoleDTO.getRole()))
                .build();
    }
}
