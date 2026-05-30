package portal.editais.dto.projeto.atividade;

import java.time.LocalDate;

import portal.editais.entity.Atividade;

public record AtividadeResponseDTO(
        Integer id,
        String descricao,
        String responsavel,
        LocalDate dataInicio,
        LocalDate dataFim) {

    public static AtividadeResponseDTO toResponse(Atividade entity) {
        return new AtividadeResponseDTO(
                entity.getId(),
                entity.getDescricao(),
                entity.getResponsavel(),
                entity.getDataInicio(),
                entity.getDataFim());
    }
}