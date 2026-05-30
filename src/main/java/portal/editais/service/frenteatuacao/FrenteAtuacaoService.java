package portal.editais.service.frenteatuacao;

import java.util.List;
import portal.editais.dto.edital.FrenteAtuacaoResponseDTO;

public interface FrenteAtuacaoService {
    List<FrenteAtuacaoResponseDTO> listarFrentesAtuacaoAtivas();
}
