package portal.editais.dto.projeto.planoexecucao;

import java.util.Collections;
import java.util.List;

import portal.editais.dto.projeto.atividade.AtividadeResponseDTO;
import portal.editais.entity.PlanoExecucao;

public record PlanoExecucaoResponseDTO(
        Integer id,
        String objetivoGeral,
        String objetivoEspecifico,
        List<AtividadeResponseDTO> atividades) {

    public static PlanoExecucaoResponseDTO toResponse(
            PlanoExecucao entity) {

        List<AtividadeResponseDTO> atividades = entity.getAtividades() == null
                ? Collections.emptyList()
                : entity.getAtividades()
                        .stream()
                        .map(AtividadeResponseDTO::toResponse)
                        .toList();

        return new PlanoExecucaoResponseDTO(
                entity.getId(),
                entity.getObjetivoGeral(),
                entity.getObjetivoEspecifico(),
                atividades);
    }
}