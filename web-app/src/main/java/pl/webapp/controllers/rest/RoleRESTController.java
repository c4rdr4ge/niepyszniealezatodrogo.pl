package pl.webapp.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.buisness.dto.RoleDTO;
import pl.webapp.buisness.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleRESTController {

    RoleService roleService;

    @GetMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping(value = "/role-by-id/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoleDTO getRoleById(@PathVariable Integer roleId) {
        return roleService.getRoleById(roleId);
    }
}
