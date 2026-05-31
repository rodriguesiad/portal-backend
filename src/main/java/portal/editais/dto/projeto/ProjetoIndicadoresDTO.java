package portal.editais.dto.projeto;

public record ProjetoIndicadoresDTO(
        Long totalSubmissoes,
        Long submetidos,
        Long emAvaliacao,
        Long aprovados,
        Long reprovados,
        Long emExecucao) {
}