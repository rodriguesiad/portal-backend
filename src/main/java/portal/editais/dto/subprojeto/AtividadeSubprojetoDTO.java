package portal.editais.dto.subprojeto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AtividadeSubprojetoDTO(
    @NotBlank @Size(max = 200) String nome,
    @Size(max = 200) String responsavel,
    LocalDate inicio,
    LocalDate fim,
    @Size(max = 1000) String descricao
) {
}
