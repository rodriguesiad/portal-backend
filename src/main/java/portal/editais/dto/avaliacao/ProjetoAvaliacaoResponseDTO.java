package portal.editais.dto.avaliacao;

import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusSubprojeto;

public record ProjetoAvaliacaoResponseDTO(
    Integer id,
    String nomeProjeto,
    String proponente,
    String edital,
    String resumo,
    StatusSubprojeto status
) {
    public static ProjetoAvaliacaoResponseDTO toResponse(Projeto projeto) {
        return new ProjetoAvaliacaoResponseDTO(
            projeto.getId(),
            projeto.getNomeProjeto(),
            projeto.getAutor() != null ? projeto.getAutor().getNome() : null,
            projeto.getEdital() != null ? projeto.getEdital().getTitulo() : null,
            projeto.getResumo(),
            projeto.getStatus()
        );
    }
}
