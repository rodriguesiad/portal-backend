package portal.editais.dto.edital;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriterioAvaliacaoDTO(
    @NotBlank @Size(max = 150) String nome,
    @Size(max = 1000) String descricao,
    @NotNull Integer ordem
) {
}
