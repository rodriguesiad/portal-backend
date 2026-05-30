package portal.editais.dto.subprojeto;

import jakarta.validation.Valid;
import portal.editais.dto.publico.PublicoBeneficiadoDTO;

public record SubprojetoEtapa4DTO(

        @Valid PublicoBeneficiadoDTO publicoBeneficiado) {
}
