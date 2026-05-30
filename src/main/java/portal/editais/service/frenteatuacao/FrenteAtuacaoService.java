package portal.editais.service.frenteatuacao;

import java.util.List;
import portal.editais.dto.frenteatuacao.FrenteAtuacaoResponseDTO;

public interface FrenteAtuacaoService {
    List<FrenteAtuacaoResponseDTO> listarFrentesAtuacaoAtivas();
}
