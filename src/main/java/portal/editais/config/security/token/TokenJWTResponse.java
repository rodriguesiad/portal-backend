package portal.editais.config.security.token;

import lombok.Builder;
import lombok.Data;

/**
 * Classe que representa a resposta contendo o token JWT gerado após o login do usuário.
 * Este modelo é retornado para o cliente como parte da resposta do endpoint de autenticação.
 *
 * @author Iad Rodrigues
 */
@Data
@Builder
public class TokenJWTResponse {

    private String token;

    public TokenJWTResponse(String token) {
        this.token = token;
    }

}
