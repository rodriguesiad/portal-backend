package portal.editais.dto.subprojeto;

import portal.editais.dto.publico.PublicoBeneficiadoResponseDTO;
import portal.editais.entity.Subprojeto;

public record SubprojetoResponseEtapa4DTO(
                Integer id,
                PublicoBeneficiadoResponseDTO publicoBeneficiadoResponseDTO) {
        public static SubprojetoResponseEtapa4DTO toResponse(Subprojeto entity) {
                return new SubprojetoResponseEtapa4DTO(
                                entity.getId(),
                                entity.getPublicoBeneficiado() != null
                                                ? PublicoBeneficiadoResponseDTO
                                                                .toResponse(entity.getPublicoBeneficiado())
                                                : null);
        }
}
