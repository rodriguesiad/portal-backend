package portal.editais.dto.avaliacao;

import jakarta.validation.constraints.NotNull;

public record SelecionarVencedorDTO(
    @NotNull Integer projetoId,
    @NotNull Integer auditorId
) {
}
