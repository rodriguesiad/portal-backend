package portal.editais.dto.documento;

import portal.editais.entity.Documento;
import portal.editais.entity.DocumentoEdital;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentoEditalResponseDTO(
    Integer id,
    Integer editalId,
    UUID documentoId,
    String nomeOriginal,
    String tipoConteudo,
    Long tamanhoBytes,
    String url,
    LocalDateTime criadoEm
) {
    public static DocumentoEditalResponseDTO toResponse(DocumentoEdital documentoEdital) {
        Documento documento = documentoEdital.getDocumento();
        return new DocumentoEditalResponseDTO(
                documentoEdital.getId(),
                documentoEdital.getEdital().getId(),
                documento.getId(),
                documento.getNomeOriginal(),
                documento.getTipoConteudo(),
                documento.getTamanhoBytes(),
                documento.getUrl(),
                documentoEdital.getCriadoEm()
        );
    }
}
