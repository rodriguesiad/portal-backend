package portal.editais.dto.subprojeto;

import jakarta.validation.Valid;
import portal.editais.dto.instituicao.InstituicaoDTO;

public record SubprojetoEtapa1DTO(
        @Valid InstituicaoDTO instituicao) {
}
