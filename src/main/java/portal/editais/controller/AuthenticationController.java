package portal.editais.controller;

import jakarta.validation.Valid;
import portal.editais.config.security.token.TokenJWTResponse;
import portal.editais.config.security.token.TokenService;
import portal.editais.dto.auth.AuthDTO;
import portal.editais.entity.User;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager manager;
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@Valid @RequestBody AuthDTO request) throws RuntimeException {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken(((User) authentication.getPrincipal()));

        return ResponseEntity.ok(new TokenJWTResponse(tokenJWT));
    }
}
