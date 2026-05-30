package portal.editais.dto.edital;

import portal.editais.entity.Municipio;

public record MunicipioResponseDTO(
        Integer id,
        String nome,
        RegiaoImediataResponseDTO regiaoImediata) {

    public static MunicipioResponseDTO toResponse(Municipio entity) {
        return new MunicipioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getRegiaoImediata() != null ? RegiaoImediataResponseDTO.toResponse(entity.getRegiaoImediata())
                        : null);
    }
}
