package portal.editais.dto.regiaoimediata;

import portal.editais.entity.RegiaoImediata;

public record RegiaoImediataResponseDTO(
        Integer id,
    String codigo,
    String nome
) {
    public static RegiaoImediataResponseDTO toResponse(RegiaoImediata regiaoImediata) {
        return new RegiaoImediataResponseDTO(
                regiaoImediata.getId(),
                regiaoImediata.getCodigo(),
                regiaoImediata.getNome()
        );
    }
}
