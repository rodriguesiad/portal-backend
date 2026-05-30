package portal.editais.dto.planoexecucao;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import portal.editais.dto.atividade.AtividadeDTO;

public record PlanoExecucaoDTO(

        @NotBlank(message = "O objetivo geral é obrigatório.") @Size(max = 2000, message = "O objetivo geral não pode ter mais de 2000 caracteres.") String objetivoGeral,

        @NotBlank(message = "O objetivo específico é obrigatório.") @Size(max = 3000, message = "O objetivo específico não pode ter mais de 3000 caracteres.") String objetivoEspecifico,

        @Valid @NotEmpty(message = "Informe pelo menos uma atividade.") List<AtividadeDTO> atividades) {
}