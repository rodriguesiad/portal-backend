package portal.editais.dto.projeto.natureza;

import portal.editais.entity.NaturezaJuridica;

public record NaturezaJuridicaResponseDTO(
        Integer id,
        String codigo,
        String nome) {

    public static NaturezaJuridicaResponseDTO toResponse(NaturezaJuridica entity) {
        return new NaturezaJuridicaResponseDTO(
                entity.getId(),
                entity.getCodigo(),
                entity.getNome());
    }

}
