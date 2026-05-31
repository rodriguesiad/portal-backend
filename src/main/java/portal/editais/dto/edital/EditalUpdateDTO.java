package portal.editais.dto.edital;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record EditalUpdateDTO(
    @NotBlank @Size(max = 255) String titulo,
    @NotNull Integer frenteAtuacaoId,
    @NotNull Integer regiaoImediataId,
    @NotNull Integer orgaoProponenteId,
    @NotEmpty List<Integer> avaliadoresIds,
    @NotEmpty List<CriterioAvaliacaoDTO> criterios,
    @NotEmpty List<UUID> documentosIds,
    @NotNull @DecimalMin("0.01") BigDecimal valorMinimo,
    @NotNull @DecimalMin("0.01") BigDecimal valorMaximo,
    @NotNull LocalDate inicioRecebimentoPropostas,
    @NotNull LocalDate fimRecebimentoPropostas,
    @NotBlank String resumo
) {
}
