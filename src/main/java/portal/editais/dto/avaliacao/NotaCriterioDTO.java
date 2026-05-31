package portal.editais.dto.avaliacao;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record NotaCriterioDTO(
    @NotNull Integer criterioId,
    @NotNull @DecimalMin("1.00") @DecimalMax("10.00") BigDecimal nota,
    @Size(max = 1000) String comentario
) {
}
