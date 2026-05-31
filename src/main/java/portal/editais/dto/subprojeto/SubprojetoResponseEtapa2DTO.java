package portal.editais.dto.subprojeto;

import java.util.List;
import portal.editais.entity.Subprojeto;
import portal.editais.enumeration.StatusSubprojeto;

public record SubprojetoResponseEtapa2DTO(
        Integer id,
        String nomeSubprojeto,
        Integer edital,
        String resumo,
        String justificativaMerito,
        StatusSubprojeto status,
        List<AtividadeSubprojetoResponseDTO> atividades) {
    public static SubprojetoResponseEtapa2DTO toResponse(Subprojeto entity) {
        return new SubprojetoResponseEtapa2DTO(
                entity.getId(),
                entity.getNomeSubprojeto(),
                entity.getEdital() != null ? entity.getEdital().getId() : null,
                entity.getResumo(),
                entity.getJustificativaMerito(),
                entity.getStatus(),
                entity.getAtividades().stream().map(AtividadeSubprojetoResponseDTO::toResponse).toList());
    }
}
