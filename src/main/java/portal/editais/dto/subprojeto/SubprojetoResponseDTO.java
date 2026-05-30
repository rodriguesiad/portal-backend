package portal.editais.dto.subprojeto;

import java.time.LocalDateTime;

import portal.editais.dto.subprojeto.instituicao.InstituicaoResponseDTO;
import portal.editais.dto.user.UserResponseDTO;
import portal.editais.entity.Subprojeto;

public record SubprojetoResponseDTO(
        Integer id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        InstituicaoResponseDTO instituicao,
        UserResponseDTO autor) {
    public static SubprojetoResponseDTO toResponse(Subprojeto entity) {
        return new SubprojetoResponseDTO(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getInstituicao() != null
                        ? InstituicaoResponseDTO.toResponse(entity.getInstituicao())
                        : null,
                entity.getAutor() != null ? UserResponseDTO.toResponse(entity.getAutor()) : null);
    }
}
