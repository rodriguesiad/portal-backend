package portal.editais.dto.subprojeto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record SubprojetoEtapa2DTO(

        @Size(max = 200, message = "O campo nome do subprojeto nao pode ter mais de 200 caracteres.")
        @NotBlank(message = "O campo nome do subprojeto nao pode ser vazio.")
        String nomeSubprojeto,

        @NotNull(message = "O campo edital nao pode ser vazio.")
        Integer edital,

        @Size(max = 1500, message = "O campo resumo nao pode ter mais de 1500 caracteres.")
        String resumo,

        @Size(max = 1500, message = "O campo justificativa de merito nao pode ter mais de 1500 caracteres.")
        @NotBlank(message = "O campo justificativa de merito do subprojeto nao pode ser vazio.")
        String justificativaMerito,

        @Valid
        List<AtividadeSubprojetoDTO> atividades) {
}
