package pl.webapp.buisness.services;

import jakarta.persistence.EntityNotFoundException;
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

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleService {

    UserRoleRepository userRoleRepository;
    UserRoleMapper userRoleMapper;

    UserMapper userMapper;
    RoleMapper roleMapper;

    @Transactional
    public List<UserRoleDTO> getAllUserRoles() {
        return userRoleRepository.findAll().stream()
                .map(userRole -> userRoleMapper.map(userRole))
                .toList();
    }

    @Transactional
    public Integer getRoleIdByUserId(Integer userId) {
        return userRoleRepository.findAll().stream()
                .filter(userRole -> userId.equals(userRole.getUser().getUserId()))
                .map(userRole -> userRole.getRole().getRoleId())
                .findAny().orElseThrow(() -> new EntityNotFoundException("RoleId with userId: [%s], not found".formatted(userId)));
    }

    @Transactional
    public void addNewUserRole(UserRoleDTO userRoleDTO) {
        UserRoleEntity newUserRole = UserRoleEntity.builder()
                .user(userMapper.map(userRoleDTO.getUser()))
                .role(roleMapper.map(userRoleDTO.getRole()))
                .build();

        userRoleRepository.save(newUserRole);
    }
}
