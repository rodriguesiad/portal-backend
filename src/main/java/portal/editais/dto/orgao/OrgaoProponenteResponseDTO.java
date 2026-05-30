package portal.editais.dto.orgao;

import portal.editais.entity.OrgaoProponente;

public record OrgaoProponenteResponseDTO(
    Integer id,
    String codigo,
    String nome
) {
    public static OrgaoProponenteResponseDTO toResponse(OrgaoProponente orgaoProponente) {
        return new OrgaoProponenteResponseDTO(
                orgaoProponente.getId(),
                orgaoProponente.getCodigo(),
                orgaoProponente.getNome()
        );
    }
}
