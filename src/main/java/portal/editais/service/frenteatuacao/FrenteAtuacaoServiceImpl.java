package portal.editais.service.frenteatuacao;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.editais.dto.frenteatuacao.FrenteAtuacaoResponseDTO;
import portal.editais.entity.FrenteAtuacao;
import portal.editais.repository.FrenteAtuacaoRepository;

@Service
public class FrenteAtuacaoServiceImpl implements FrenteAtuacaoService {

    private final FrenteAtuacaoRepository frenteAtuacaoRepository;

    public FrenteAtuacaoServiceImpl(FrenteAtuacaoRepository frenteAtuacaoRepository) {
        this.frenteAtuacaoRepository = frenteAtuacaoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FrenteAtuacaoResponseDTO> listarFrentesAtuacaoAtivas() {
        return frenteAtuacaoRepository.findByAtivoTrueOrderByNomeAsc()
            .stream()
            .map(FrenteAtuacaoResponseDTO::toResponse)
            .toList();
    }
}
