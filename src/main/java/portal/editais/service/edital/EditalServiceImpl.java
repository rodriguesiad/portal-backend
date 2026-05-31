package portal.editais.service.edital;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import portal.editais.dto.documento.DocumentoEditalResponseDTO;
import portal.editais.dto.documento.DocumentoVinculadoResponseDTO;
import portal.editais.dto.edital.CriterioAvaliacaoDTO;
import portal.editais.dto.edital.EditalDTO;
import portal.editais.dto.edital.EditalResponseDTO;
import portal.editais.dto.edital.EditalResumoResponseDTO;
import portal.editais.dto.edital.EditalUpdateDTO;
import portal.editais.entity.CriterioAvaliacao;
import portal.editais.entity.Documento;
import portal.editais.entity.DocumentoEdital;
import portal.editais.entity.Edital;
import portal.editais.entity.Estado;
import portal.editais.entity.FrenteAtuacao;
import portal.editais.entity.OrgaoProponente;
import portal.editais.entity.RegiaoImediata;
import portal.editais.entity.User;
import portal.editais.enumeration.ContextoDocumento;
import portal.editais.enumeration.Profile;
import portal.editais.enumeration.SituacaoEdital;
import portal.editais.enumeration.StatusEdital;
import portal.editais.repository.DocumentoEditalRepository;
import portal.editais.repository.DocumentoRepository;
import portal.editais.repository.EditalRepository;
import portal.editais.repository.EstadoRepository;
import portal.editais.repository.FrenteAtuacaoRepository;
import portal.editais.repository.OrgaoProponenteRepository;
import portal.editais.repository.RegiaoImediataRepository;
import portal.editais.repository.UserRepository;
import portal.editais.specification.EditalSpecifications;

@Service
public class EditalServiceImpl implements EditalService {

    private static final String SIGLA_TOCANTINS = "TO";

    private final EditalRepository editalRepository;
    private final EstadoRepository estadoRepository;
    private final OrgaoProponenteRepository orgaoProponenteRepository;
    private final FrenteAtuacaoRepository frenteAtuacaoRepository;
    private final RegiaoImediataRepository regiaoImediataRepository;
    private final DocumentoRepository documentoRepository;
    private final DocumentoEditalRepository documentoEditalRepository;
    private final UserRepository userRepository;

    public EditalServiceImpl(
            EditalRepository editalRepository,
            EstadoRepository estadoRepository,
            OrgaoProponenteRepository orgaoProponenteRepository,
            FrenteAtuacaoRepository frenteAtuacaoRepository,
            RegiaoImediataRepository regiaoImediataRepository,
            DocumentoRepository documentoRepository,
            DocumentoEditalRepository documentoEditalRepository,
            UserRepository userRepository) {
        this.editalRepository = editalRepository;
        this.estadoRepository = estadoRepository;
        this.orgaoProponenteRepository = orgaoProponenteRepository;
        this.frenteAtuacaoRepository = frenteAtuacaoRepository;
        this.regiaoImediataRepository = regiaoImediataRepository;
        this.documentoRepository = documentoRepository;
        this.documentoEditalRepository = documentoEditalRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public EditalResponseDTO criarEdital(EditalDTO dto) {
        validarValores(dto.valorMinimo(), dto.valorMaximo());
        validarPeriodo(dto.inicioRecebimentoPropostas(), dto.fimRecebimentoPropostas());
        validarDocumentosObrigatorios(dto.documentosIds());
        validarCriterios(dto.criterios());

        Estado tocantins = buscarEstadoTocantins();
        OrgaoProponente orgaoProponente = buscarOrgaoProponente(dto.orgaoProponenteId());
        if (!SIGLA_TOCANTINS.equals(orgaoProponente.getEstado().getSigla())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Órgão proponente deve pertencer ao Tocantins.");
        }

        Edital edital = Edital.builder()
                .titulo(dto.titulo())
                .estado(tocantins)
                .orgaoProponente(orgaoProponente)
                .frenteAtuacao(buscarFrenteAtuacao(dto.frenteAtuacaoId()))
                .regiaoImediata(buscarRegiaoImediata(dto.regiaoImediataId()))
                .avaliadores(buscarAvaliadores(dto.avaliadoresIds()))
                .valorMinimo(dto.valorMinimo())
                .valorMaximo(dto.valorMaximo())
                .inicioRecebimentoPropostas(dto.inicioRecebimentoPropostas())
                .fimRecebimentoPropostas(dto.fimRecebimentoPropostas())
                .resumo(dto.resumo())
                .status(StatusEdital.RASCUNHO)
                .build();
        aplicarCriterios(edital, dto.criterios());

        Edital editalSalvo = editalRepository.save(edital);
        vincularDocumentosAoEdital(editalSalvo, dto.documentosIds());

        List<DocumentoVinculadoResponseDTO> documentos = documentoEditalRepository.findByEditalId(edital.getId())
                .stream()
                .map(documentoEdital -> new DocumentoVinculadoResponseDTO(
                        documentoEdital.getDocumento().getId(),
                        documentoEdital.getDocumento().getNomeOriginal()))
                .toList();

        return EditalResponseDTO.toResponse(editalRepository.save(edital), documentos);
    }

    @Override
    @Transactional
    public EditalResponseDTO atualizarEdital(Integer id, EditalUpdateDTO dto) {
        validarValores(dto.valorMinimo(), dto.valorMaximo());
        validarPeriodo(dto.inicioRecebimentoPropostas(), dto.fimRecebimentoPropostas());
        validarDocumentosObrigatorios(dto.documentosIds());
        validarCriterios(dto.criterios());

        Edital edital = buscarEdital(id);
        if (edital.getStatus() != StatusEdital.RASCUNHO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Somente editais em rascunho podem ser atualizados.");
        }

        OrgaoProponente orgaoProponente = buscarOrgaoProponente(dto.orgaoProponenteId());
        if (!SIGLA_TOCANTINS.equals(orgaoProponente.getEstado().getSigla())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Órgão proponente deve pertencer ao Tocantins.");
        }

        edital.setTitulo(dto.titulo());
        edital.setOrgaoProponente(orgaoProponente);
        edital.setFrenteAtuacao(buscarFrenteAtuacao(dto.frenteAtuacaoId()));
        edital.setRegiaoImediata(buscarRegiaoImediata(dto.regiaoImediataId()));
        edital.setAvaliadores(buscarAvaliadores(dto.avaliadoresIds()));
        edital.setValorMinimo(dto.valorMinimo());
        edital.setValorMaximo(dto.valorMaximo());
        edital.setInicioRecebimentoPropostas(dto.inicioRecebimentoPropostas());
        edital.setFimRecebimentoPropostas(dto.fimRecebimentoPropostas());
        edital.setResumo(dto.resumo());
        aplicarCriterios(edital, dto.criterios());

        documentoEditalRepository.deleteAll(documentoEditalRepository.findByEditalId(edital.getId()));
        vincularDocumentosAoEdital(edital, dto.documentosIds());

        List<DocumentoVinculadoResponseDTO> documentos = documentoEditalRepository.findByEditalId(edital.getId())
                .stream()
                .map(documentoEdital -> new DocumentoVinculadoResponseDTO(
                        documentoEdital.getDocumento().getId(),
                        documentoEdital.getDocumento().getNomeOriginal()))
                .toList();

        return EditalResponseDTO.toResponse(editalRepository.save(edital), documentos);
    }

    @Override
    @Transactional
    public List<DocumentoEditalResponseDTO> vincularDocumentos(Integer id, List<UUID> documentosIds) {
        Edital edital = buscarEdital(id);
        validarDocumentosObrigatorios(documentosIds);
        return vincularDocumentosAoEdital(edital, documentosIds);
    }

    @Override
    @Transactional(readOnly = true)
    public EditalResponseDTO buscarEditalPorId(Integer id) {
        Edital edital = buscarEdital(id);
        List<DocumentoVinculadoResponseDTO> documentos = documentoEditalRepository.findByEditalId(edital.getId())
                .stream()
                .map(documentoEdital -> new DocumentoVinculadoResponseDTO(
                        documentoEdital.getDocumento().getId(),
                        documentoEdital.getDocumento().getNomeOriginal()))
                .toList();

        return EditalResponseDTO.toResponse(edital, documentos);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EditalResumoResponseDTO> listarEditaisPublicos(
            SituacaoEdital situacao,
            Integer frenteAtuacaoId,
            Integer regiaoImediataId,
            Integer orgaoProponenteId,
            LocalDate inicioRecebimento,
            LocalDate fimRecebimento,
            Pageable pageable) {
        Specification<Edital> specification = EditalSpecifications.publico()
                .and(EditalSpecifications.comSituacaoPublica(situacao))
                .and(EditalSpecifications.comFrenteAtuacao(frenteAtuacaoId))
                .and(EditalSpecifications.comRegiaoImediata(regiaoImediataId))
                .and(EditalSpecifications.comOrgaoProponente(orgaoProponenteId))
                .and(EditalSpecifications.comInicioRecebimento(inicioRecebimento))
                .and(EditalSpecifications.comFimRecebimento(fimRecebimento));

        return editalRepository.findAll(specification, pageable).map(EditalResumoResponseDTO::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EditalResumoResponseDTO> listarEditaisAdministrativos(
            StatusEdital status,
            Integer frenteAtuacaoId,
            Integer regiaoImediataId,
            Integer orgaoProponenteId,
            LocalDate inicioRecebimento,
            LocalDate fimRecebimento,
            Pageable pageable) {
        Specification<Edital> specification = EditalSpecifications.comStatus(status)
                .and(EditalSpecifications.comFrenteAtuacao(frenteAtuacaoId))
                .and(EditalSpecifications.comRegiaoImediata(regiaoImediataId))
                .and(EditalSpecifications.comOrgaoProponente(orgaoProponenteId))
                .and(EditalSpecifications.comInicioRecebimento(inicioRecebimento))
                .and(EditalSpecifications.comFimRecebimento(fimRecebimento));

        return editalRepository.findAll(specification, pageable).map(EditalResumoResponseDTO::toResponse);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void atualizarStatusPorDatas() {
        LocalDate hoje = LocalDate.now();

        List<Edital> editaisParaAbrir = editalRepository.findByStatusAndInicioRecebimentoPropostasLessThanEqual(
                StatusEdital.RASCUNHO,
                hoje);
        editaisParaAbrir.forEach(edital -> edital.setStatus(StatusEdital.ABERTO));
        editalRepository.saveAll(editaisParaAbrir);

        List<Edital> editaisParaAvaliacao = editalRepository.findByStatusAndFimRecebimentoPropostasBefore(
                StatusEdital.ABERTO,
                hoje);
        editaisParaAvaliacao.forEach(edital -> edital.setStatus(StatusEdital.EM_AVALIACAO));
        editalRepository.saveAll(editaisParaAvaliacao);
    }

    private List<DocumentoEditalResponseDTO> vincularDocumentosAoEdital(Edital edital, List<UUID> documentosIds) {
        List<Documento> documentos = documentoRepository.findAllById(documentosIds);
        if (documentos.size() != new HashSet<>(documentosIds).size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um ou mais documentos não foram encontrados.");
        }

        boolean documentoComContextoInvalido = documentos.stream()
                .anyMatch(documento -> documento.getContexto() != ContextoDocumento.EDITAL);

        if (documentoComContextoInvalido) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Todos os documentos devem possuir contexto EDITAL.");
        }

        return documentos.stream()
                .map(documento -> documentoEditalRepository.save(DocumentoEdital.builder()
                        .edital(edital)
                        .documento(documento)
                        .build()))
                .map(DocumentoEditalResponseDTO::toResponse)
                .toList();
    }

    private Edital buscarEdital(Integer id) {
        return editalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Edital não encontrado."));
    }

    private Estado buscarEstadoTocantins() {
        return estadoRepository.findBySiglaAndAtivoTrue(SIGLA_TOCANTINS)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Estado do Tocantins não configurado."));
    }

    private OrgaoProponente buscarOrgaoProponente(Integer id) {
        return orgaoProponenteRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Órgão proponente inválido."));
    }

    private FrenteAtuacao buscarFrenteAtuacao(Integer id) {
        return frenteAtuacaoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Frente de atuação inválida."));
    }

    private RegiaoImediata buscarRegiaoImediata(Integer id) {
        return regiaoImediataRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Região imediata inválida."));
    }

    private Set<User> buscarAvaliadores(List<Integer> avaliadoresIds) {
        if (avaliadoresIds == null || avaliadoresIds.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe ao menos um avaliador.");
        }

        List<User> usuarios = new ArrayList<>();
        avaliadoresIds.forEach(x -> {
            User usuario = userRepository.findById(x).isPresent() ? userRepository.findById(x).get() : null;
            if (usuario != null) {
                usuarios.add(usuario);
            }
        });

        if (usuarios.size() != new HashSet<>(avaliadoresIds).size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um ou mais avaliadores não foram encontrados.");
        }

        boolean existeUsuarioSemPerfilAvaliador = usuarios.stream()
                .anyMatch(usuario -> usuario.getProfile() != Profile.AVALIADOR);

        if (existeUsuarioSemPerfilAvaliador) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Todos os avaliadores devem possuir perfil AVALIADOR.");
        }

        return new HashSet<>(usuarios);
    }

    private void validarValores(BigDecimal valorMinimo, BigDecimal valorMaximo) {
        if (valorMinimo != null && valorMaximo != null && valorMinimo.compareTo(valorMaximo) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Valor mínimo não pode ser maior que o valor máximo.");
        }
    }

    private void validarPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio != null && fim != null && inicio.isAfter(fim)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Início do recebimento não pode ser posterior ao fim.");
        }
    }

    private void validarDocumentosObrigatorios(List<UUID> documentosIds) {
        if (documentosIds == null || documentosIds.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe ao menos um documento PDF.");
        }
    }

    private void validarCriterios(List<CriterioAvaliacaoDTO> criterios) {
        if (criterios == null || criterios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe ao menos um critério de avaliação.");
        }
    }

    private void aplicarCriterios(Edital edital, List<CriterioAvaliacaoDTO> criterios) {
        edital.getCriterios().clear();
        criterios.stream()
                .sorted(Comparator.comparing(CriterioAvaliacaoDTO::ordem))
                .forEach(dto -> edital.getCriterios().add(CriterioAvaliacao.builder()
                        .edital(edital)
                        .nome(dto.nome())
                        .descricao(dto.descricao())
                        .ordem(dto.ordem())
                        .build()));
    }
}
