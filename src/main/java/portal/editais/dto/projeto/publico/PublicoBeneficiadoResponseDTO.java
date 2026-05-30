package portal.editais.dto.projeto.publico;

import portal.editais.entity.PublicoBeneficiado;

public record PublicoBeneficiadoResponseDTO(
        Integer id,
        Integer mulheresQuant,
        Integer homensQuant,
        Integer criancasQuant,
        Integer jovensQuant,
        Integer idososQuant,
        Integer povosIndigenasQuant,
        Integer quilombolasQuant,
        Integer agricultoresFamiliarQuant,
        Integer comunidadesTradicionaisQuant,
        Float rendaMedia,
        String fonteRendaPrincipal,
        String descricaoAplicacaoBeneficio) {

    public static PublicoBeneficiadoResponseDTO toResponse(PublicoBeneficiado entity) {
        return new PublicoBeneficiadoResponseDTO(
                entity.getId(),
                entity.getMulheresQuant(),
                entity.getHomensQuant(),
                entity.getCriancasQuant(),
                entity.getJovensQuant(),
                entity.getIdososQuant(),
                entity.getPovosIndigenasQuant(),
                entity.getQuilombolasQuant(),
                entity.getAgricultoresFamiliarQuant(),
                entity.getComunidadesTradicionaisQuant(),
                entity.getRendaMedia(),
                entity.getFonteRendaPrincipal(),
                entity.getDescricaoAplicacaoBeneficio());
    }

}