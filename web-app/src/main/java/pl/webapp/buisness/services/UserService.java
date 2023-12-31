package pl.webapp.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.buisness.dto.UserDTO;
import pl.webapp.buisness.dto.mappers.UserMapper;
import pl.webapp.infrastructure.database.entity.UserEntity;
import pl.webapp.infrastructure.database.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Transactional
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userMapper.map(user))
                .toList();
    }

    @Transactional
    public UserDTO getUserByUserName(String username) {
        List<UserEntity> all = userRepository.findAll();
        return all.stream()
                .filter(user -> username.equals(user.getUserUsername()))
                .map(user -> userMapper.map(user))
                .findAny().orElseThrow(() -> new EntityNotFoundException("User with username: [%s], not found.". formatted(username)));
    }

    @Transactional
    public void addNewUser(UserDTO userDTO) {
        UserEntity newUser = UserEntity.builder()
                .userUsername(userDTO.getUserUsername())
                .userPassword(userDTO.getUserPassword())
                .userName(userDTO.getUserName())
                .userSurname(userDTO.getUserSurname())
                .userPhone(userDTO.getUserPhone())
                .userEmail(userDTO.getUserEmail())
                .userAddressId(userDTO.getUserAddressId())
                .build();

        userRepository.save(newUser);
    }
}
