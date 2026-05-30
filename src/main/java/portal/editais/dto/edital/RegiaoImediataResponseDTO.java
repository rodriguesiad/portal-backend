package portal.editais.dto.edital;

import portal.editais.entity.RegiaoImediata;

public record RegiaoImediataResponseDTO(
        Long id,
        String codigo,
        String nome) {

    public static RegiaoImediataResponseDTO toResponse(RegiaoImediata entity) {
        return new RegiaoImediataResponseDTO(
                entity.getId(),
                entity.getCodigo(),
                entity.getNome());
    }
}
