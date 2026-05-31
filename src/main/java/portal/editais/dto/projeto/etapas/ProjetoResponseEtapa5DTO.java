package portal.editais.dto.projeto.etapas;

import portal.editais.dto.projeto.planoexecucao.PlanoExecucaoResponseDTO;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.SituacaoProjeto;

public record ProjetoResponseEtapa5DTO(
                Integer id,
                SituacaoProjeto situacao,
                PlanoExecucaoResponseDTO planoExecucaoResponseDTO) {
        public static ProjetoResponseEtapa5DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa5DTO(
                                entity.getId(),
                                entity.getSituacao(),
                                entity.getPlanoExecucao() != null
                                                ? PlanoExecucaoResponseDTO.toResponse(entity.getPlanoExecucao())
                                                : null);
        }
}
