package portal.editais.dto.projeto.etapas;

import java.time.LocalDateTime;

import portal.editais.dto.projeto.instituicao.InstituicaoResponseDTO;
import portal.editais.dto.user.UserResponseDTO;
import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

public record ProjetoResponseEtapa1DTO(
                Integer id,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                StatusProjeto status,
                InstituicaoResponseDTO instituicao,
                UserResponseDTO autor) {
        public static ProjetoResponseEtapa1DTO toResponse(Projeto entity) {
                return new ProjetoResponseEtapa1DTO(
                                entity.getId(),
                                entity.getCreatedAt(),
                                entity.getUpdatedAt(),
                                entity.getStatus(),
                                entity.getInstituicao() != null
                                                ? InstituicaoResponseDTO.toResponse(entity.getInstituicao())
                                                : null,
                                entity.getAutor() != null ? UserResponseDTO.toResponse(entity.getAutor()) : null);
        }
}
