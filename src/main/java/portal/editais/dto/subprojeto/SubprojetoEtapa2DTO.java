package portal.editais.dto.subprojeto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubprojetoEtapa2DTO(

        @Size(max = 200, message = "O campo nome do subprojeto não pode ter mais de 200 caracteres.")
        @NotBlank(message = "O campo nome do subprojeto não pode ser vazio.")
        String nomeSubprojeto,

        @NotNull(message = "O campo edital não pode ser vazio.")
        Integer edital,

        @Size(max = 1500, message = "O campo resumo não pode ter mais de 1500 caracteres.")
        String resumo,

        @Size(max = 1500, message = "O campo justificativa de mérito não pode ter mais de 1500 caracteres.")
        @NotBlank(message = "O campo justificativa de mérito do subprojeto não pode ser vazio.")
        String justificativaMerito) {
}
