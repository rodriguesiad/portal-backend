package portal.editais.dto.projeto;

import java.time.LocalDateTime;
import java.util.UUID;
import portal.editais.entity.Evidencia;
import portal.editais.enumeration.StatusEvidencia;

public record EvidenciaResponseDTO(
    Integer id,
    Integer atividadeId,
    String atividade,
    UUID fotoDocumentoId,
    String descricao,
    StatusEvidencia status,
    String comentarioAuditor,
    String validadoPor,
    LocalDateTime criadoEm,
    LocalDateTime atualizadoEm
) {
    public static EvidenciaResponseDTO toResponse(Evidencia evidencia) {
        return new EvidenciaResponseDTO(
            evidencia.getId(),
            evidencia.getAtividade() != null ? evidencia.getAtividade().getId() : null,
            evidencia.getAtividade() != null ? evidencia.getAtividade().getNome() : null,
            evidencia.getFoto().getId(),
            evidencia.getDescricao(),
            evidencia.getStatus(),
            evidencia.getComentarioAuditor(),
            evidencia.getValidadoPor() != null ? evidencia.getValidadoPor().getNome() : null,
            evidencia.getCriadoEm(),
            evidencia.getAtualizadoEm()
        );
    }
}
