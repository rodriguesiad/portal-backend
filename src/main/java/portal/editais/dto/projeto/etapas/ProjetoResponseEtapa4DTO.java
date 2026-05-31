package portal.editais.dto.projeto.etapas;

import portal.editais.dto.projeto.publico.PublicoBeneficiadoResponseDTO;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

public record ProjetoResponseEtapa4DTO(
                Integer id,
                StatusProjeto status,
                PublicoBeneficiadoResponseDTO publicoBeneficiadoResponseDTO) {
        public static ProjetoResponseEtapa4DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa4DTO(
                                entity.getId(),
                                entity.getStatus(),
                                entity.getPublicoBeneficiado() != null
                                                ? PublicoBeneficiadoResponseDTO
                                                                .toResponse(entity.getPublicoBeneficiado())
                                                : null);
        }
}
