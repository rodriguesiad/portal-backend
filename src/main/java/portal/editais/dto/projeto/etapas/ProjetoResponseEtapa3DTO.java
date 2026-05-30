package portal.editais.dto.projeto.etapas;

import portal.editais.dto.projeto.localizacao.LocalizacaoResponseDTO;
import portal.editais.entity.Projeto;

public record ProjetoResponseEtapa3DTO(
                Integer id,
                LocalizacaoResponseDTO localizacaoResponseDTO) {
        public static ProjetoResponseEtapa3DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa3DTO(
                                entity.getId(),
                                entity.getLocalizacao() != null
                                                ? LocalizacaoResponseDTO.toResponse(entity.getLocalizacao())
                                                : null);
        }
}
