package portal.editais.dto.projeto.etapas;

import portal.editais.dto.projeto.planoexecucao.PlanoExecucaoResponseDTO;
import portal.editais.entity.Projeto;

public record ProjetoResponseEtapa5DTO(
                Integer id,
                PlanoExecucaoResponseDTO planoExecucaoResponseDTO) {
        public static ProjetoResponseEtapa5DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa5DTO(
                                entity.getId(),
                                entity.getPlanoExecucao() != null
                                                ? PlanoExecucaoResponseDTO.toResponse(entity.getPlanoExecucao())
                                                : null);
        }
}
