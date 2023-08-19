package pl.webapp.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.buisness.dto.RoleDTO;
import pl.webapp.buisness.dto.mappers.RoleMapper;
import pl.webapp.infrastructure.database.entity.RoleEntity;
import pl.webapp.infrastructure.database.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @Transactional
    public List<RoleDTO> getAllRoles(){
        return roleRepository.findAll().stream()
                .map(role -> roleMapper.map(role))
                .toList();
    }

    @Transactional
    public RoleDTO getRoleById(Integer roleId) {
        Optional<RoleEntity> role = roleRepository.findById(roleId);
        if (role.isPresent()) {
            return roleMapper.map(role.get());
        }else {
            throw new EntityNotFoundException("Role with roleId: [%s], not found.".formatted(roleId));
        }
    }
}
