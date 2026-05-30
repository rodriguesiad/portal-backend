package portal.editais.dto.projeto.etapas;

import jakarta.validation.Valid;
import portal.editais.dto.projeto.instituicao.InstituicaoDTO;

public record ProjetoEtapa1DTO(
        @Valid InstituicaoDTO instituicao) {
}
