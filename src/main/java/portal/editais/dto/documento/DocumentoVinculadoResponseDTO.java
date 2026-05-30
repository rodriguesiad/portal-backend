package portal.editais.dto.documento;

import java.util.UUID;

public record DocumentoVinculadoResponseDTO(
    UUID id,
    String nomeOriginal
) {
}
