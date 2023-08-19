package pl.webapp.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.webapp.buisness.dto.UserRoleDTO;
import pl.webapp.buisness.services.UserRoleService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleRESTController {

    UserRoleService userRoleService;

    @GetMapping(value = "/user-role", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRoleDTO> getAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }

    @PostMapping(value = "/user-role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoleDTO> addNewUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        userRoleService.addNewUserRole(userRoleDTO);
        return new ResponseEntity<>(userRoleDTO, HttpStatus.CREATED);
    }
}
