package portal.editais.dto.subprojeto;

import java.time.LocalDate;
import portal.editais.entity.AtividadeSubprojeto;

public record AtividadeSubprojetoResponseDTO(
    Integer id,
    String nome,
    String responsavel,
    LocalDate inicio,
    LocalDate fim,
    String descricao
) {
    public static AtividadeSubprojetoResponseDTO toResponse(AtividadeSubprojeto atividade) {
        return new AtividadeSubprojetoResponseDTO(
            atividade.getId(),
            atividade.getNome(),
            atividade.getResponsavel(),
            atividade.getInicio(),
            atividade.getFim(),
            atividade.getDescricao()
        );
    }
}
