package portal.editais.service.avaliacao;

import java.util.List;
import portal.editais.dto.avaliacao.AvaliacaoDTO;
import portal.editais.dto.avaliacao.PropostaAvaliacaoResponseDTO;
import portal.editais.dto.avaliacao.RankingPropostaResponseDTO;
import portal.editais.dto.avaliacao.SelecionarVencedorDTO;
import portal.editais.dto.edital.EditalResumoResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;

public interface AvaliacaoService {
    List<EditalResumoResponseDTO> listarEditaisDoAvaliador();

    List<PropostaAvaliacaoResponseDTO> listarPropostas(Integer editalId);

    void salvarAvaliacao(Integer subprojetoId, AvaliacaoDTO dto);

    List<RankingPropostaResponseDTO> ranking(Integer editalId);

    ProjetoResponseDTO selecionarVencedor(Integer editalId, SelecionarVencedorDTO dto);
}
