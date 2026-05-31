package portal.editais.dto.projeto.etapas;

import jakarta.validation.Valid;
import portal.editais.dto.projeto.planoexecucao.PlanoExecucaoDTO;

public record ProjetoEtapa5DTO(

        @Valid PlanoExecucaoDTO planoExecucao) {
}
