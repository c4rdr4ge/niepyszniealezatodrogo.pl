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

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Transactional
    public UserDTO getUserByUserName(String username) {
        return userRepository.findAll().stream()
                .filter(user -> username.equals(user.getUserName()))
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
    }
}
