package portal.editais.dto.projeto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import portal.editais.enumeration.StatusEvidencia;

public record ValidarEvidenciaDTO(
    @NotNull StatusEvidencia status,
    @Size(max = 1000) String comentario
) {
}
