package portal.editais.dto.atividade;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import portal.editais.validations.DataFimMaiorQueInicio;

@DataFimMaiorQueInicio
public record AtividadeDTO(

        @NotBlank(message = "A descrição da atividade é obrigatória.") @Size(max = 1500, message = "A descrição não pode ter mais de 1500 caracteres.") String descricao,

        @NotBlank(message = "O responsável pela atividade é obrigatório.") @Size(max = 200, message = "O responsável não pode ter mais de 200 caracteres.") String responsavel,

        @NotNull(message = "A data de início é obrigatória.") @FutureOrPresent(message = "A data de início deve ser hoje ou uma data futura.") LocalDate dataInicio,

        @NotNull(message = "A data de fim é obrigatória.") @FutureOrPresent(message = "A data de fim deve ser hoje ou uma data futura.") LocalDate dataFim) {
}