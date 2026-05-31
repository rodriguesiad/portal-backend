package portal.editais.dto.projeto.etapas;

import jakarta.validation.Valid;
import portal.editais.dto.projeto.localizacao.LocalizacaoDTO;

public record ProjetoEtapa3DTO(

                @Valid LocalizacaoDTO localizacao) {
}
