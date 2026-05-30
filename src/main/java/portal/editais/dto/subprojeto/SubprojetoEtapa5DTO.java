package portal.editais.dto.subprojeto;

import jakarta.validation.Valid;
import portal.editais.dto.planoexecucao.PlanoExecucaoDTO;

public record SubprojetoEtapa5DTO(

        @Valid PlanoExecucaoDTO planoExecucao) {
}
