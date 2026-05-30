package portal.editais.dto.projeto.etapas;

import portal.editais.dto.projeto.publico.PublicoBeneficiadoResponseDTO;
import portal.editais.entity.Projeto;

public record ProjetoResponseEtapa4DTO(
                Integer id,
                PublicoBeneficiadoResponseDTO publicoBeneficiadoResponseDTO) {
        public static ProjetoResponseEtapa4DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa4DTO(
                                entity.getId(),
                                entity.getPublicoBeneficiado() != null
                                                ? PublicoBeneficiadoResponseDTO
                                                                .toResponse(entity.getPublicoBeneficiado())
                                                : null);
        }
}
