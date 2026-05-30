package portal.editais.dto.localizacao;

import portal.editais.entity.Localizacao;

public record LocalizacaoResponseDTO(
        Integer id,
        String latitude,
        String longitude,
        String municipio,
        String comunidade) {

    public static LocalizacaoResponseDTO toResponse(Localizacao entity) {
        return new LocalizacaoResponseDTO(
                entity.getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getMunicipio(),
                entity.getComunidade());
    }

}
