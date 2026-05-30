package portal.editais.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
                @NotBlank(message = "O campo nome não pode ser vazio.")
                @Size(max = 200, message = "O campo nome não pode ter mais de 200 caracteres.")
                String name,

                @Email
                @NotBlank(message = "O campo e-mail não pode ser vazio.")
                @Size(max = 200, message = "O campo email não pode ter mais de 200 caracteres.")
                String email,

                @NotBlank(message = "O campo senha não pode ser vazio.")
                @Size(max = 200, message = "O campo senha não pode ter mais de 200 caracteres.")
                String password) {
}
