package portal.editais.dto.avaliacao;

import java.math.BigDecimal;

public record RankingPropostaResponseDTO(
    Integer projetoId,
    String nomeProjeto,
    String proponente,
    BigDecimal notaFinal,
    boolean avaliacaoCompleta,
    long pendencias
) {
}
