package portal.editais.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.user.UserDTO;
import portal.editais.dto.user.UserResponseDTO;
import portal.editais.entity.User;
import portal.editais.enumeration.Profile;
import portal.editais.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários")
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

    @GetMapping(path = "/perfil")
    public ResponseEntity<List<UserResponseDTO>> listarUsuariosPorPerfil(@RequestParam Profile profile) throws ApiException {
        return ResponseEntity.ok(service.listarUsuariosPorPerfil(profile).stream().map(UserResponseDTO::toResponse).toList());
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
