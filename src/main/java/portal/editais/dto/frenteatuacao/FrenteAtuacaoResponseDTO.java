package portal.editais.dto.frenteatuacao;

import portal.editais.entity.FrenteAtuacao;

public record FrenteAtuacaoResponseDTO(
    Integer id,
    String codigo,
    String nome
) {
    public static FrenteAtuacaoResponseDTO toResponse(FrenteAtuacao frenteAtuacao) {
        return new FrenteAtuacaoResponseDTO(
                frenteAtuacao.getId(),
                frenteAtuacao.getCodigo(),
                frenteAtuacao.getNome()
        );
    }
}
