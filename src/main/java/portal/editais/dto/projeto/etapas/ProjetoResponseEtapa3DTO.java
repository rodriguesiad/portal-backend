package portal.editais.dto.projeto.etapas;

import portal.editais.dto.projeto.localizacao.LocalizacaoResponseDTO;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

public record ProjetoResponseEtapa3DTO(
                Integer id,
                StatusProjeto status,
                LocalizacaoResponseDTO localizacaoResponseDTO) {
        public static ProjetoResponseEtapa3DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa3DTO(
                                entity.getId(),
                                entity.getStatus(),
                                entity.getLocalizacao() != null
                                                ? LocalizacaoResponseDTO.toResponse(entity.getLocalizacao())
                                                : null);
        }
}
