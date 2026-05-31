package portal.editais.dto.avaliacao;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record AvaliacaoDTO(
    @Valid @NotEmpty List<NotaCriterioDTO> notas
) {
}
