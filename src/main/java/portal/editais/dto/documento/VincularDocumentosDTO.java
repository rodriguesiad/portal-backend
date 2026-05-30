package portal.editais.dto.documento;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

public record VincularDocumentosDTO(
    @NotEmpty List<UUID> documentosIds
) {
}
