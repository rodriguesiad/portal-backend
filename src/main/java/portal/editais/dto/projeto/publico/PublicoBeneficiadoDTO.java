package portal.editais.dto.projeto.publico;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PublicoBeneficiadoDTO(

        @NotNull(message = "O campo quantidade de mulheres é obrigatório.")
        @Min(value = 0, message = "A quantidade de mulheres não pode ser negativa.")
        Integer mulheresQuant,

        @NotNull(message = "O campo quantidade de homens é obrigatório.")
        @Min(value = 0, message = "A quantidade de homens não pode ser negativa.")
        Integer homensQuant,

        @NotNull(message = "O campo quantidade de crianças é obrigatório.")
        @Min(value = 0, message = "A quantidade de crianças não pode ser negativa.")
        Integer criancasQuant,

        @NotNull(message = "O campo quantidade de jovens é obrigatório.")
        @Min(value = 0, message = "A quantidade de jovens não pode ser negativa.")
        Integer jovensQuant,

        @NotNull(message = "O campo quantidade de idosos é obrigatório.")
        @Min(value = 0, message = "A quantidade de idosos não pode ser negativa.")
        Integer idososQuant,

        @NotNull(message = "O campo quantidade de povos indígenas é obrigatório.")
        @Min(value = 0, message = "A quantidade de povos indígenas não pode ser negativa.")
        Integer povosIndigenasQuant,

        @NotNull(message = "O campo quantidade de quilombolas é obrigatório.")
        @Min(value = 0, message = "A quantidade de quilombolas não pode ser negativa.")
        Integer quilombolasQuant,

        @NotNull(message = "O campo quantidade de agricultores familiares é obrigatório.")
        @Min(value = 0, message = "A quantidade de agricultores familiares não pode ser negativa.")
        Integer agricultoresFamiliarQuant,

        @NotNull(message = "O campo quantidade de comunidades tradicionais é obrigatório.")
        @Min(value = 0, message = "A quantidade de comunidades tradicionais não pode ser negativa.")
        Integer comunidadesTradicionaisQuant,

        @Min(value = 0, message = "A renda média não pode ser negativa.")
        Float rendaMedia,

        @Size(max = 100, message = "O campo fonte de renda principal não pode ter mais de 100 caracteres.")
        String fonteRendaPrincipal,

        @NotBlank(message = "O campo descrição da aplicação do benefício não pode ser vazio.")
        @Size(max = 1500, message = "O campo descrição da aplicação do benefício não pode ter mais de 1500 caracteres.")
        String descricaoAplicacaoBeneficio

) {
}