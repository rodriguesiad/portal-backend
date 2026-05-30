package portal.editais.dto.localizacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocalizacaoDTO(
                @Size(max = 100, message = "O campo latitude não pode ter mais de 100 caracteres.") String latitude,

                @Size(max = 100, message = "O campo longitude não pode ter mais de 1000 caracteres.") String longitude,

                @NotBlank(message = "O campo municipio não pode ser vazio.") String municipio,

                @NotBlank(message = "O campo comunidade não pode ser vazio.") @Size(max = 500, message = "O campo comunidade não pode ter mais de 500 caracteres.") String comunidade) {
}
