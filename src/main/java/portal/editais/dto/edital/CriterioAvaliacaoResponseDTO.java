package portal.editais.dto.edital;

import portal.editais.entity.CriterioAvaliacao;

public record CriterioAvaliacaoResponseDTO(
    Integer id,
    String nome,
    String descricao,
    Integer ordem
) {
    public static CriterioAvaliacaoResponseDTO toResponse(CriterioAvaliacao criterio) {
        return new CriterioAvaliacaoResponseDTO(
            criterio.getId(),
            criterio.getNome(),
            criterio.getDescricao(),
            criterio.getOrdem()
        );
    }
}
