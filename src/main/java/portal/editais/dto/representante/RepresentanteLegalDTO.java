package portal.editais.dto.representante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RepresentanteLegalDTO(
        @NotBlank(message = "O campo nome completo não pode ser vazio.")
        @Size(max = 200, message = "O campo nome completo não pode ter mais de 200 caracteres.")
        String nomeCompleto,

        @NotBlank(message = "O campo CPF não pode ser vazio.")
        @Size(max = 14, message = "O campo CPF não pode ter mais de 14 caracteres.")
        String cpf,

        @Size(max = 100, message = "O campo RG não pode ter mais de 100 caracteres.")
        String rg,

        @Email(message = "O e-mail informado é inválido.")
        @NotBlank(message = "O campo e-mail não pode ser vazio.")
        @Size(max = 200, message = "O campo e-mail não pode ter mais de 200 caracteres.")
        String email,

        @NotBlank(message = "O campo telefone não pode ser vazio.")
        @Size(max = 24, message = "O campo telefone não pode ter mais de 24 caracteres.")
        String telefone,

        @NotBlank(message = "O campo cargo não pode ser vazio.")
        @Size(max = 200, message = "O campo cargo não pode ter mais de 200 caracteres.")
        String cargo) {
}
