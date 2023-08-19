package pl.webapp.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.webapp.buisness.dto.UserDTO;
import pl.webapp.buisness.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRESTController {

    UserService userService;

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user-by-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUserName(username);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addNewUser(@RequestBody UserDTO userDTO) {
        userService.addNewUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
