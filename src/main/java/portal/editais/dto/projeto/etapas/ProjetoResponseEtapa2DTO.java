package portal.editais.dto.projeto.etapas;

import portal.editais.dto.edital.EditalResumoResponseDTO;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.SituacaoProjeto;

public record ProjetoResponseEtapa2DTO(
                Integer id,
                SituacaoProjeto situacao,
                String nomeProjeto,
                EditalResumoResponseDTO edital,
                String resumo,
                String justificativaMerito) {
        public static ProjetoResponseEtapa2DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa2DTO(
                                entity.getId(),
                                entity.getSituacao(),
                                entity.getNomeProjeto(),
                                entity.getEdital() != null ? EditalResumoResponseDTO.toResponse(entity.getEdital())
                                                : null,
                                entity.getResumo(),
                                entity.getJustificativaMerito());
        }
}
