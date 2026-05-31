package portal.editais.dto.projeto.instituicao;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import portal.editais.dto.representante.RepresentanteLegalDTO;

public record InstituicaoDTO(

                @Size(max = 200, message = "O campo nome fantasia não pode ter mais de 200 caracteres.") String nomeFantasia,

                @NotBlank(message = "O campo razão social não pode ser vazio.") @Size(max = 200, message = "O campo razão social não pode ter mais de 200 caracteres.") String razaoSocial,

                @NotBlank(message = "O campo CNPJ não pode ser vazio.") @Size(max = 18, message = "O campo CNPJ não pode ter mais de 18 caracteres.") String cnpj,

                @PastOrPresent(message = "A data de fundação não pode ser futura.")
                LocalDate dataFundacao,

                @NotNull(message = "O campo natureza jurídica é obrigatório.") Integer idNaturezaJuridica,

                @Size(max = 500, message = "O campo área de atuação não pode ter mais de 500 caracteres.") String areaAtuacao,

                @Size(max = 255, message = "O campo site não pode ter mais de 255 caracteres.") String site,

                @Size(max = 500, message = "O campo redes sociais não pode ter mais de 500 caracteres.") String redesSociais,

                @Valid RepresentanteLegalDTO representanteLegal

) {
}