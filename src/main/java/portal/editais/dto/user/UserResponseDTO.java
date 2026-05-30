package portal.editais.dto.user;

import java.time.LocalDateTime;

import portal.editais.entity.User;
import portal.editais.enumeration.Profile;

public record UserResponseDTO(
        Integer id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String nome,
        String email,
        Profile profile) {

    public static UserResponseDTO toResponse(User entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getNome(),
                entity.getEmail(),
                entity.getProfile());
    }

}
