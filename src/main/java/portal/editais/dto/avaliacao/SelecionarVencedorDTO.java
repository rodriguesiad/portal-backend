package portal.editais.dto.avaliacao;

import jakarta.validation.constraints.NotNull;

public record SelecionarVencedorDTO(
    @NotNull Integer subprojetoId,
    @NotNull Integer auditorId
) {
}
