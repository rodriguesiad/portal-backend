package portal.editais.dto.subprojeto;

import java.time.LocalDateTime;

import portal.editais.dto.instituicao.InstituicaoResponseDTO;
import portal.editais.dto.user.UserResponseDTO;
import portal.editais.entity.Subprojeto;

public record SubprojetoResponseEtapa1DTO(
        Integer id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        InstituicaoResponseDTO instituicao,
        UserResponseDTO autor) {
    public static SubprojetoResponseEtapa1DTO toResponse(Subprojeto entity) {
        return new SubprojetoResponseEtapa1DTO(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getInstituicao() != null
                        ? InstituicaoResponseDTO.toResponse(entity.getInstituicao())
                        : null,
                entity.getAutor() != null ? UserResponseDTO.toResponse(entity.getAutor()) : null);
    }
}
