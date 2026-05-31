package portal.editais.dto.projeto.etapas;

import portal.editais.dto.edital.EditalResumoResponseDTO;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.SituacaoProjeto;

public record ProjetoResponseEtapa2DTO(
                Integer id,
                SituacaoProjeto situacao,
                String nomeProjeto,
                Integer edital,
                String resumo,
                String justificativaMerito) {
        public static ProjetoResponseEtapa2DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa2DTO(
                                entity.getId(),
                                entity.getSituacao(),
                                entity.getNomeProjeto(),
                                entity.getEdital() != null ? entity.getEdital().getId() : null,
                                entity.getResumo(),
                                entity.getJustificativaMerito());
        }
}
