package portal.editais.dto.subprojeto;

import jakarta.validation.Valid;
import portal.editais.dto.localizacao.LocalizacaoDTO;

public record SubprojetoEtapa3DTO(

                @Valid LocalizacaoDTO localizacao) {
}
