package portal.editais.dto.projeto;

import java.time.LocalDate;
import portal.editais.entity.AtividadeProjeto;
import portal.editais.enumeration.StatusAtividadeProjeto;

public record AtividadeProjetoResponseDTO(
    Integer id,
    String nome,
    String responsavel,
    LocalDate inicio,
    LocalDate fim,
    String descricao,
    StatusAtividadeProjeto status
) {
    public static AtividadeProjetoResponseDTO toResponse(AtividadeProjeto atividade) {
        return new AtividadeProjetoResponseDTO(
            atividade.getId(),
            atividade.getNome(),
            atividade.getResponsavel(),
            atividade.getInicio(),
            atividade.getFim(),
            atividade.getDescricao(),
            atividade.getStatus()
        );
    }
}
