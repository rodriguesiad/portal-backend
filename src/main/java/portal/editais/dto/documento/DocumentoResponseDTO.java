package portal.editais.dto.documento;

import java.time.LocalDateTime;
import java.util.UUID;

import portal.editais.entity.Documento;
import portal.editais.entity.DocumentoEdital;
import portal.editais.enumeration.ContextoDocumento;

public record DocumentoResponseDTO(
    UUID id,
    String nomeOriginal,
    String tipoConteudo,
    Long tamanhoBytes,
    String bucket,
    String objectKey,
    String url,
    ContextoDocumento contexto,
    LocalDateTime criadoEm
) {
}
