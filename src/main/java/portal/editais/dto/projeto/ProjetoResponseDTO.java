package portal.editais.dto.projeto;

import java.time.LocalDateTime;
import java.util.List;
import portal.editais.entity.Projeto;

public record ProjetoResponseDTO(
    Integer id,
    Integer subprojetoId,
    String nome,
    String proponente,
    String auditor,
    LocalDateTime criadoEm,
    List<AtividadeProjetoResponseDTO> atividades,
    List<EvidenciaResponseDTO> evidencias
) {
    public static ProjetoResponseDTO toResponse(Projeto projeto, List<EvidenciaResponseDTO> evidencias) {
        return new ProjetoResponseDTO(
            projeto.getId(),
            projeto.getSubprojeto().getId(),
            projeto.getSubprojeto().getNomeSubprojeto(),
            projeto.getSubprojeto().getAutor().getNome(),
            projeto.getAuditor().getNome(),
            projeto.getCriadoEm(),
            projeto.getAtividades().stream().map(AtividadeProjetoResponseDTO::toResponse).toList(),
            evidencias
        );
    }
}
