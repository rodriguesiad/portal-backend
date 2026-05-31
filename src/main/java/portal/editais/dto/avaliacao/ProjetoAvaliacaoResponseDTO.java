package portal.editais.dto.avaliacao;

import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

public record ProjetoAvaliacaoResponseDTO(
    Integer id,
    String nomeProjeto,
    String proponente,
    String edital,
    String resumo,
    String latitude,
    String longitude,
    String regiaoImediata,
    StatusProjeto status
) {
    public static ProjetoAvaliacaoResponseDTO toResponse(Projeto projeto) {
        return new ProjetoAvaliacaoResponseDTO(
            projeto.getId(),
            projeto.getNomeProjeto(),
            projeto.getAutor() != null ? projeto.getAutor().getNome() : null,
            projeto.getEdital() != null ? projeto.getEdital().getTitulo() : null,
            projeto.getResumo(),
            projeto.getLocalizacao() != null ? projeto.getLocalizacao().getLatitude() : null,
            projeto.getLocalizacao() != null ? projeto.getLocalizacao().getLongitude() : null,
            projeto.getEdital() != null && projeto.getEdital().getRegiaoImediata() != null
                ? projeto.getEdital().getRegiaoImediata().getNome()
                : null,
            projeto.getStatus()
        );
    }
}
