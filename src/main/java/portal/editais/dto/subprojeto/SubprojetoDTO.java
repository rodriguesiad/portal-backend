package portal.editais.dto.subprojeto;

import jakarta.validation.Valid;
import portal.editais.dto.subprojeto.instituicao.InstituicaoDTO;

public record SubprojetoDTO(
        @Valid InstituicaoDTO instituicao) {
}
