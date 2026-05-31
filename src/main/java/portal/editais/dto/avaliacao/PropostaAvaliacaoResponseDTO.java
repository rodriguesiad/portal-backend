package portal.editais.dto.avaliacao;

import portal.editais.entity.Subprojeto;
import portal.editais.enumeration.StatusSubprojeto;

public record PropostaAvaliacaoResponseDTO(
    Integer id,
    String nomeSubprojeto,
    String proponente,
    String edital,
    String resumo,
    StatusSubprojeto status
) {
    public static PropostaAvaliacaoResponseDTO toResponse(Subprojeto subprojeto) {
        return new PropostaAvaliacaoResponseDTO(
            subprojeto.getId(),
            subprojeto.getNomeSubprojeto(),
            subprojeto.getAutor() != null ? subprojeto.getAutor().getNome() : null,
            subprojeto.getEdital() != null ? subprojeto.getEdital().getTitulo() : null,
            subprojeto.getResumo(),
            subprojeto.getStatus()
        );
    }
}
