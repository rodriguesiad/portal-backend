package portal.editais.dto.projeto.etapas;

import jakarta.validation.Valid;
import portal.editais.dto.projeto.publico.PublicoBeneficiadoDTO;

public record ProjetoEtapa4DTO(

        @Valid PublicoBeneficiadoDTO publicoBeneficiado) {
}
