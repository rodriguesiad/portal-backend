package portal.editais.dto.projeto.etapas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjetoEtapa2DTO(

        @Size(max = 200, message = "O campo nome do projeto não pode ter mais de 200 caracteres.")
        @NotBlank(message = "O campo nome do projeto não pode ser vazio.")
        String nomeProjeto,

        @NotNull(message = "O campo edital não pode ser vazio.")
        Integer idEdital,

        @Size(max = 1500, message = "O campo resumo não pode ter mais de 1500 caracteres.")
        String resumo,

        @Size(max = 1500, message = "O campo justificativa de mérito não pode ter mais de 1500 caracteres.")
        @NotBlank(message = "O campo justificativa de mérito do projeto não pode ser vazio.")
        String justificativaMerito) {
}
