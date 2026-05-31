package portal.editais.dto.projeto.etapas;

import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

public record ProjetoResponseEtapa2DTO(
                Integer id,
                StatusProjeto status,
                String nomeProjeto,
                Integer edital,
                String resumo,
                String justificativaMerito) {
        public static ProjetoResponseEtapa2DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa2DTO(
                                entity.getId(),
                                entity.getStatus(),
                                entity.getNomeProjeto(),
                                entity.getEdital() != null ? entity.getEdital().getId() : null,
                                entity.getResumo(),
                                entity.getJustificativaMerito());
        }
}
