package portal.editais.dto.subprojeto;

import portal.editais.dto.planoexecucao.PlanoExecucaoResponseDTO;
import portal.editais.entity.Subprojeto;

public record SubprojetoResponseEtapa5DTO(
                Integer id,
                PlanoExecucaoResponseDTO planoExecucaoResponseDTO) {
        public static SubprojetoResponseEtapa5DTO toResponse(Subprojeto entity) {
                return new SubprojetoResponseEtapa5DTO(
                                entity.getId(),
                                entity.getPlanoExecucao() != null
                                                ? PlanoExecucaoResponseDTO.toResponse(entity.getPlanoExecucao())
                                                : null);
        }
}
