package portal.editais.dto.avaliacao;

import java.math.BigDecimal;

public record RankingPropostaResponseDTO(
    Integer subprojetoId,
    String nomeSubprojeto,
    String proponente,
    BigDecimal notaFinal,
    boolean avaliacaoCompleta,
    long pendencias
) {
}
