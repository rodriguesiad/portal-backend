package portal.editais.dto.subprojeto;

import portal.editais.dto.localizacao.LocalizacaoResponseDTO;
import portal.editais.entity.Subprojeto;

public record SubprojetoResponseEtapa3DTO(
                Integer id,
                LocalizacaoResponseDTO localizacaoResponseDTO) {
        public static SubprojetoResponseEtapa3DTO toResponse(Subprojeto entity) {
                return new SubprojetoResponseEtapa3DTO(
                                entity.getId(),
                                entity.getLocalizacao() != null
                                                ? LocalizacaoResponseDTO.toResponse(entity.getLocalizacao())
                                                : null);
        }
}
