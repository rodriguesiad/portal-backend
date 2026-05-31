package portal.editais.dto.projeto;

public record ProjetoIndicadoresDTO(
        Long totalSubmissoes,
        Long emAnalise,
        Long aprovados,
        Long reprovados,
        Long analisado) {
}