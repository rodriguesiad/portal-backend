package portal.editais.dto.projeto;

import java.time.LocalDateTime;
import java.util.List;
import portal.editais.entity.Projeto;

public record ProjetoResponseDTO(
    Integer id,
    Integer editalId,
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
            projeto.getEdital() != null ? projeto.getEdital().getId() : null,
            projeto.getNomeProjeto(),
            projeto.getAutor() != null ? projeto.getAutor().getNome() : null,
            projeto.getAuditor() != null ? projeto.getAuditor().getNome() : null,
            projeto.getCreatedAt(),
            projeto.getAtividades().stream().map(AtividadeProjetoResponseDTO::toResponse).toList(),
            evidencias
        );
    }
}
