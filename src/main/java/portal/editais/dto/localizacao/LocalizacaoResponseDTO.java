package portal.editais.dto.localizacao;

import portal.editais.dto.edital.MunicipioResponseDTO;
import portal.editais.entity.Localizacao;

public record LocalizacaoResponseDTO(
        Integer id,
        String latitude,
        String longitude,
        MunicipioResponseDTO municipio,
        String comunidade) {

    public static LocalizacaoResponseDTO toResponse(Localizacao entity) {
        return new LocalizacaoResponseDTO(
                entity.getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getMunicipio() != null ? MunicipioResponseDTO.toResponse(entity.getMunicipio()) : null,
                entity.getComunidade());
    }

}
