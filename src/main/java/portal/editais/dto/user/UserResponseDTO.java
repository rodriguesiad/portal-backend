package portal.editais.dto.user;

import java.time.LocalDateTime;

import portal.editais.entity.User;

public record UserResponseDTO(
        Integer id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String nome,
        String email) {

    public static UserResponseDTO toResponse(User entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getNome(),
                entity.getEmail());
    }

}
