package portal.editais.service.avaliacao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import portal.editais.dto.avaliacao.AvaliacaoDTO;
import portal.editais.dto.avaliacao.NotaCriterioDTO;
import portal.editais.dto.avaliacao.ProjetoAvaliacaoResponseDTO;
import portal.editais.dto.avaliacao.RankingPropostaResponseDTO;
import portal.editais.dto.avaliacao.SelecionarVencedorDTO;
import portal.editais.dto.edital.EditalResumoResponseDTO;
import portal.editais.dto.projeto.EvidenciaResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.entity.Atividade;
import portal.editais.entity.AtividadeProjeto;
import portal.editais.entity.AvaliacaoCriterio;
import portal.editais.entity.CriterioAvaliacao;
import portal.editais.entity.Edital;
import portal.editais.entity.Projeto;
import portal.editais.entity.User;
import portal.editais.enumeration.Profile;
import portal.editais.enumeration.StatusEdital;
import portal.editais.enumeration.StatusProjeto;
import portal.editais.repository.AvaliacaoCriterioRepository;
import portal.editais.repository.CriterioAvaliacaoRepository;
import portal.editais.repository.EditalRepository;
import portal.editais.repository.EvidenciaRepository;
import portal.editais.repository.ProjetoRepository;
import portal.editais.repository.UserRepository;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final EditalRepository editalRepository;
    private final ProjetoRepository projetoRepository;
    private final CriterioAvaliacaoRepository criterioRepository;
    private final AvaliacaoCriterioRepository avaliacaoRepository;
    private final UserRepository userRepository;
    private final EvidenciaRepository evidenciaRepository;

    public AvaliacaoServiceImpl(
            EditalRepository editalRepository,
            ProjetoRepository projetoRepository,
            CriterioAvaliacaoRepository criterioRepository,
            AvaliacaoCriterioRepository avaliacaoRepository,
            UserRepository userRepository,
            EvidenciaRepository evidenciaRepository) {
        this.editalRepository = editalRepository;
        this.projetoRepository = projetoRepository;
        this.criterioRepository = criterioRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.userRepository = userRepository;
        this.evidenciaRepository = evidenciaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EditalResumoResponseDTO> listarEditaisDoAvaliador() {
        return editalRepository.findByAvaliadoresId(getLoggedInUser().getId())
            .stream()
            .map(EditalResumoResponseDTO::toResponse)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetoAvaliacaoResponseDTO> listarProjetos(Integer editalId) {
        validarAvaliadorDoEdital(editalId);
        return projetoRepository.findByEditalId(editalId).stream()
            .map(ProjetoAvaliacaoResponseDTO::toResponse)
            .toList();
    }

    @Override
    @Transactional
    public void salvarAvaliacao(Integer projetoId, AvaliacaoDTO dto) {
        User avaliador = getLoggedInUser();
        Projeto projeto = buscarProjeto(projetoId);
        Edital edital = projeto.getEdital();
        if (edital == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Projeto sem edital vinculado.");
        }
        validarAvaliadorDoEdital(edital.getId());
        if (edital.getStatus() != StatusEdital.EM_AVALIACAO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Edital ainda nao esta em avaliacao.");
        }

        Set<Integer> criteriosDoEdital = criterioRepository.findByEditalIdOrderByOrdemAsc(edital.getId())
            .stream()
            .map(CriterioAvaliacao::getId)
            .collect(Collectors.toSet());
        Set<Integer> criteriosInformados = dto.notas().stream()
            .map(NotaCriterioDTO::criterioId)
            .collect(Collectors.toSet());
        if (!criteriosInformados.equals(criteriosDoEdital)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe nota de 1 a 10 para todos os criterios do edital.");
        }

        Map<Integer, CriterioAvaliacao> criterios = criterioRepository.findAllById(criteriosInformados).stream()
            .collect(Collectors.toMap(CriterioAvaliacao::getId, criterio -> criterio));
        dto.notas().forEach(nota -> {
            AvaliacaoCriterio avaliacao = avaliacaoRepository
                .findByProjetoIdAndCriterioIdAndAvaliadorId(projetoId, nota.criterioId(), avaliador.getId())
                .orElseGet(AvaliacaoCriterio::new);
            avaliacao.setProjeto(projeto);
            avaliacao.setCriterio(criterios.get(nota.criterioId()));
            avaliacao.setAvaliador(avaliador);
            avaliacao.setNota(nota.nota());
            avaliacao.setComentario(nota.comentario());
            avaliacaoRepository.save(avaliacao);
        });
        projeto.setStatus(StatusProjeto.EM_AVALIACAO);
        projetoRepository.save(projeto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RankingPropostaResponseDTO> ranking(Integer editalId) {
        Edital edital = validarAvaliadorDoEdital(editalId);
        return projetoRepository.findByEditalId(editalId).stream()
            .map(projeto -> montarRanking(edital, projeto))
            .sorted(Comparator.comparing(RankingPropostaResponseDTO::notaFinal).reversed())
            .toList();
    }

    @Override
    @Transactional
    public ProjetoResponseDTO selecionarVencedor(Integer editalId, SelecionarVencedorDTO dto) {
        Edital edital = validarAvaliadorDoEdital(editalId);
        User auditor = userRepository.findById(dto.auditorId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Auditor nao encontrado."));
        if (auditor.getProfile() != Profile.AUDITOR) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario selecionado nao possui perfil AUDITOR.");
        }

        Projeto vencedor = buscarProjeto(dto.projetoId());
        if (vencedor.getEdital() == null || !vencedor.getEdital().getId().equals(editalId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Projeto nao pertence ao edital.");
        }

        boolean existePendente = ranking(editalId).stream().anyMatch(item -> !item.avaliacaoCompleta());
        if (existePendente) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existem avaliacoes pendentes para este edital.");
        }

        projetoRepository.findByEditalId(editalId).forEach(projeto -> {
            projeto.setStatus(projeto.getId().equals(vencedor.getId())
                ? StatusProjeto.EM_EXECUCAO
                : StatusProjeto.REPROVADO);
            if (projeto.getId().equals(vencedor.getId())) {
                projeto.setAuditor(auditor);
                criarAtividadesDeAcompanhamento(projeto);
            }
            projetoRepository.save(projeto);
        });
        edital.setStatus(StatusEdital.ENCERRADO);
        editalRepository.save(edital);

        Projeto salvo = buscarProjeto(vencedor.getId());
        return ProjetoResponseDTO.toResponse(salvo, evidenciaRepository.findByProjetoIdOrderByCriadoEmDesc(salvo.getId())
            .stream().map(EvidenciaResponseDTO::toResponse).toList());
    }

    private RankingPropostaResponseDTO montarRanking(Edital edital, Projeto projeto) {
        List<CriterioAvaliacao> criterios = criterioRepository.findByEditalIdOrderByOrdemAsc(edital.getId());
        List<AvaliacaoCriterio> avaliacoes = avaliacaoRepository.findByProjetoId(projeto.getId());
        int totalEsperado = criterios.size() * edital.getAvaliadores().size();
        long pendencias = Math.max(0, totalEsperado - avaliacoes.size());
        BigDecimal notaFinal = avaliacoes.isEmpty()
            ? BigDecimal.ZERO
            : avaliacoes.stream()
                .map(AvaliacaoCriterio::getNota)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(avaliacoes.size()), 2, RoundingMode.HALF_UP);

        return new RankingPropostaResponseDTO(
            projeto.getId(),
            projeto.getNomeProjeto(),
            projeto.getAutor() != null ? projeto.getAutor().getNome() : null,
            notaFinal,
            pendencias == 0,
            pendencias
        );
    }

    private void criarAtividadesDeAcompanhamento(Projeto projeto) {
        if (!projeto.getAtividades().isEmpty() || projeto.getPlanoExecucao() == null || projeto.getPlanoExecucao().getAtividades() == null) {
            return;
        }
        for (Atividade origem : projeto.getPlanoExecucao().getAtividades()) {
            AtividadeProjeto atividade = new AtividadeProjeto();
            atividade.setProjeto(projeto);
            atividade.setNome(limitar(origem.getDescricao(), 200));
            atividade.setResponsavel(origem.getResponsavel());
            atividade.setInicio(origem.getDataInicio());
            atividade.setFim(origem.getDataFim());
            atividade.setDescricao(origem.getDescricao());
            projeto.getAtividades().add(atividade);
        }
    }

    private String limitar(String texto, int tamanhoMaximo) {
        if (texto == null || texto.length() <= tamanhoMaximo) {
            return texto;
        }
        return texto.substring(0, tamanhoMaximo);
    }

    private Edital validarAvaliadorDoEdital(Integer editalId) {
        User avaliador = getLoggedInUser();
        Edital edital = editalRepository.findById(editalId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Edital nao encontrado."));
        boolean vinculado = edital.getAvaliadores().stream().anyMatch(usuario -> usuario.getId().equals(avaliador.getId()));
        if (!vinculado) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Avaliador nao vinculado ao edital.");
        }
        return edital;
    }

    private Projeto buscarProjeto(Integer id) {
        return projetoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto nao encontrado."));
    }

    private User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
