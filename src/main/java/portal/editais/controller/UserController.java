package portal.editais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.user.UserDTO;
import portal.editais.dto.user.UserResponseDTO;
import portal.editais.entity.User;
import portal.editais.service.user.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    @Secured({ "ROLE_ADMINISTRADOR" })
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Integer id) throws ApiException {
        return ResponseEntity.ok(UserResponseDTO.toResponse(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserDTO request) {
        User newUser = service.create(request);
        return ResponseEntity.ok().body(UserResponseDTO.toResponse(newUser));
    }

    @GetMapping(path = "/my")
    public ResponseEntity<UserResponseDTO> getLoggedInUser() throws ApiException {
        return ResponseEntity.ok(UserResponseDTO.toResponse(service.getLoggedInUser()));
    }

}
