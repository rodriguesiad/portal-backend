package portal.editais.service.projeto;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.projeto.EvidenciaDTO;
import portal.editais.dto.projeto.EvidenciaResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.dto.projeto.ValidarEvidenciaDTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa1DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa2DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa3DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa4DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa5DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa6DTO;
import portal.editais.entity.Atividade;
import portal.editais.entity.AtividadeProjeto;
import portal.editais.entity.Documento;
import portal.editais.entity.Edital;
import portal.editais.entity.Evidencia;
import portal.editais.entity.Instituicao;
import portal.editais.entity.Localizacao;
import portal.editais.entity.Municipio;
import portal.editais.entity.PlanoExecucao;
import portal.editais.entity.PublicoBeneficiado;
import portal.editais.entity.Projeto;
import portal.editais.entity.User;
import portal.editais.enumeration.ContextoDocumento;
import portal.editais.enumeration.Profile;
import portal.editais.enumeration.StatusProjeto;
import portal.editais.repository.AtividadeProjetoRepository;
import portal.editais.repository.DocumentoRepository;
import portal.editais.repository.EditalRepository;
import portal.editais.repository.EvidenciaRepository;
import portal.editais.repository.MunicipioRepository;
import portal.editais.repository.ProjetoRepository;
import portal.editais.service.instituicao.InstituicaoService;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository repository;
    private final InstituicaoService instituicaoService;
    private final MunicipioRepository municipioRepository;
    private final EditalRepository editalRepository;
    private final DocumentoRepository documentoRepository;
    private final AtividadeProjetoRepository atividadeProjetoRepository;
    private final EvidenciaRepository evidenciaRepository;

    protected ProjetoServiceImpl(
            ProjetoRepository repository,
            InstituicaoService instituicaoService,
            MunicipioRepository municipioRepository,
            EditalRepository editalRepository,
            DocumentoRepository documentoRepository,
            AtividadeProjetoRepository atividadeProjetoRepository,
            EvidenciaRepository evidenciaRepository) {
        this.repository = repository;
        this.instituicaoService = instituicaoService;
        this.municipioRepository = municipioRepository;
        this.editalRepository = editalRepository;
        this.documentoRepository = documentoRepository;
        this.atividadeProjetoRepository = atividadeProjetoRepository;
        this.evidenciaRepository = evidenciaRepository;
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa1(ProjetoEtapa1DTO dto) throws ApiException {
        Projeto projeto = new Projeto();
        Instituicao instituicao = instituicaoService.create(dto.instituicao());

        projeto.setInstituicao(instituicao);
        projeto.setAutor(getLoggedInUser());

        return repository.save(projeto);
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa2(Integer id, ProjetoEtapa2DTO dto) throws ApiException {
        Projeto projeto = findById(id);
        validateAutor(projeto.getAutor().getId());
        validarEtapa2(projeto);

        Edital edital = editalRepository.findById(dto.edital())
                .orElseThrow(() -> new ApiException("Edital nao encontrado: " + dto.edital()));

        projeto.setNomeProjeto(dto.nomeProjeto());
        projeto.setEdital(edital);
        projeto.setResumo(dto.resumo());
        projeto.setJustificativaMerito(dto.justificativaMerito());

        return repository.save(projeto);
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa3(Integer id, ProjetoEtapa3DTO dto) throws ApiException {
        Projeto projeto = findById(id);
        validateAutor(projeto.getAutor().getId());
        validarEtapa3(projeto);

        Optional<Municipio> municipio = municipioRepository.findById(dto.localizacao().idMunicipio());

        if (municipio.isEmpty()) {
            throw new ApiException("Municipio nao encontrado");
        }

        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude(dto.localizacao().latitude());
        localizacao.setLongitude(dto.localizacao().longitude());
        localizacao.setMunicipio(municipio.get());
        localizacao.setComunidade(dto.localizacao().comunidade());

        localizacao.setProjeto(projeto);
        projeto.setLocalizacao(localizacao);

        return repository.save(projeto);
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa4(Integer id, ProjetoEtapa4DTO dto) throws ApiException {
        Projeto projeto = findById(id);
        validateAutor(projeto.getAutor().getId());
        validarEtapa4(projeto);

        PublicoBeneficiado publicoBeneficiado = new PublicoBeneficiado();

        publicoBeneficiado.setMulheresQuant(dto.publicoBeneficiado().mulheresQuant());
        publicoBeneficiado.setHomensQuant(dto.publicoBeneficiado().homensQuant());
        publicoBeneficiado.setCriancasQuant(dto.publicoBeneficiado().criancasQuant());
        publicoBeneficiado.setJovensQuant(dto.publicoBeneficiado().jovensQuant());
        publicoBeneficiado.setIdososQuant(dto.publicoBeneficiado().idososQuant());
        publicoBeneficiado.setPovosIndigenasQuant(dto.publicoBeneficiado().povosIndigenasQuant());
        publicoBeneficiado.setQuilombolasQuant(dto.publicoBeneficiado().quilombolasQuant());
        publicoBeneficiado.setAgricultoresFamiliarQuant(dto.publicoBeneficiado().agricultoresFamiliarQuant());
        publicoBeneficiado.setComunidadesTradicionaisQuant(dto.publicoBeneficiado().comunidadesTradicionaisQuant());
        publicoBeneficiado.setRendaMedia(dto.publicoBeneficiado().rendaMedia());
        publicoBeneficiado.setFonteRendaPrincipal(dto.publicoBeneficiado().fonteRendaPrincipal());
        publicoBeneficiado.setDescricaoAplicacaoBeneficio(dto.publicoBeneficiado().descricaoAplicacaoBeneficio());

        publicoBeneficiado.setProjeto(projeto);
        projeto.setPublicoBeneficiado(publicoBeneficiado);

        return repository.save(projeto);
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa5(Integer id, ProjetoEtapa5DTO dto) throws ApiException {
        Projeto projeto = findById(id);
        validateAutor(projeto.getAutor().getId());
        validarEtapa5(projeto);

        PlanoExecucao planoExecucao = new PlanoExecucao();

        planoExecucao.setObjetivoGeral(dto.planoExecucao().objetivoGeral());
        planoExecucao.setObjetivoEspecifico(dto.planoExecucao().objetivoEspecifico());

        List<Atividade> atividades = dto.planoExecucao().atividades()
                .stream()
                .map(atividadeDTO -> {
                    Atividade atividade = new Atividade();

                    atividade.setDescricao(atividadeDTO.descricao());
                    atividade.setResponsavel(atividadeDTO.responsavel());
                    atividade.setDataInicio(atividadeDTO.dataInicio());
                    atividade.setDataFim(atividadeDTO.dataFim());

                    atividade.setPlanoExecucao(planoExecucao);

                    return atividade;
                })
                .collect(Collectors.toList());

        planoExecucao.setAtividades(atividades);

        planoExecucao.setProjeto(projeto);
        projeto.setPlanoExecucao(planoExecucao);

        return repository.save(projeto);
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa6(Integer id, ProjetoEtapa6DTO dto) throws ApiException {
        Projeto projeto = findById(id);
        validateAutor(projeto.getAutor().getId());
        validarEtapa6(projeto);

        projeto.setDeclarouVeracidadeInformacoes(dto.declarouVeracidadeInformacoes());
        projeto.setAutorizouTratamentoDadosLgpd(dto.autorizouTratamentoDadosLgpd());
        projeto.setAutorizouMonitoramentoAuditoria(dto.autorizouMonitoramentoAuditoria());
        projeto.setComprometeuPrestacaoContas(dto.comprometeuPrestacaoContas());
        projeto.setStatus(StatusProjeto.SUBMETIDO);

        return repository.save(projeto);
    }

    @Override
    public Projeto findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Projeto nao encontrado: " + id));
    }

    @Override
    @Transactional
    public List<ProjetoResponseDTO> listarProjetosDoProponente() {
        return repository.findByAutorId(getLoggedInUser().getId()).stream()
                .filter(projeto -> projeto.getStatus() == StatusProjeto.EM_EXECUCAO)
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<ProjetoResponseDTO> listarProjetosDoAuditor() {
        return repository.findByAuditorId(getLoggedInUser().getId()).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public ProjetoResponseDTO buscarProjetoResponse(Integer id) {
        Projeto projeto = buscarProjeto(id);
        User usuario = getLoggedInUser();
        boolean dono = projeto.getAutor() != null && projeto.getAutor().getId().equals(usuario.getId());
        boolean auditor = projeto.getAuditor() != null && projeto.getAuditor().getId().equals(usuario.getId());
        if (!dono && !auditor && usuario.getProfile() != Profile.ADMINISTRADOR) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito ao projeto.");
        }
        return toResponse(projeto);
    }

    @Override
    @Transactional
    public ProjetoResponseDTO criarEvidencia(Integer id, EvidenciaDTO dto) {
        Projeto projeto = buscarProjeto(id);
        validateAutorRuntime(projeto);
        if (projeto.getStatus() != StatusProjeto.EM_EXECUCAO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Projeto ainda nao esta em execucao.");
        }

        Documento documento = documentoRepository.findById(dto.fotoDocumentoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento nao encontrado."));
        if (documento.getContexto() != ContextoDocumento.EVIDENCIA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento deve possuir contexto EVIDENCIA.");
        }

        AtividadeProjeto atividade = null;
        if (dto.atividadeId() != null) {
            atividade = atividadeProjetoRepository.findById(dto.atividadeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atividade nao encontrada."));
            if (!atividade.getProjeto().getId().equals(projeto.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atividade nao pertence ao projeto.");
            }
        }

        Evidencia evidencia = new Evidencia();
        evidencia.setProjeto(projeto);
        evidencia.setAtividade(atividade);
        evidencia.setFoto(documento);
        evidencia.setDescricao(dto.descricao());
        evidencia.setLatitude(dto.latitude());
        evidencia.setLongitude(dto.longitude());
        evidenciaRepository.save(evidencia);
        return toResponse(projeto);
    }

    @Override
    @Transactional
    public ProjetoResponseDTO validarEvidencia(Integer evidenciaId, ValidarEvidenciaDTO dto) {
        Evidencia evidencia = evidenciaRepository.findById(evidenciaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidencia nao encontrada."));
        Projeto projeto = evidencia.getProjeto();
        User auditor = getLoggedInUser();
        if (projeto.getAuditor() == null || !projeto.getAuditor().getId().equals(auditor.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito ao auditor do projeto.");
        }
        evidencia.setStatus(dto.status());
        evidencia.setComentarioAuditor(dto.comentario());
        evidencia.setValidadoPor(auditor);
        evidenciaRepository.save(evidencia);
        return toResponse(projeto);
    }

    private ProjetoResponseDTO toResponse(Projeto projeto) {
        return ProjetoResponseDTO.toResponse(projeto, evidenciaRepository.findByProjetoIdOrderByCriadoEmDesc(projeto.getId())
                .stream()
                .map(EvidenciaResponseDTO::toResponse)
                .toList());
    }

    private Projeto buscarProjeto(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto nao encontrado."));
    }

    private void validateAutor(Integer id) throws ApiException {
        if (!getLoggedInUser().getId().equals(id)) {
            throw new ApiException("Acao restrita ao dono do registro.");
        }
    }

    private void validateAutorRuntime(Projeto projeto) {
        if (projeto.getAutor() == null || !getLoggedInUser().getId().equals(projeto.getAutor().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acao restrita ao dono do registro.");
        }
    }

    private void validarEtapa2(Projeto projeto) throws ApiException {
        if (projeto.getNomeProjeto() != null) {
            throw new ApiException("A etapa 2 ja foi preenchida para este projeto.");
        }
    }

    private void validarEtapa3(Projeto projeto) throws ApiException {
        if (projeto.getLocalizacao() != null) {
            throw new ApiException("A localizacao ja foi cadastrada para este projeto.");
        }
    }

    private void validarEtapa4(Projeto projeto) throws ApiException {
        if (projeto.getPublicoBeneficiado() != null) {
            throw new ApiException("O publico beneficiado ja foi cadastrado para este projeto.");
        }
    }

    private void validarEtapa5(Projeto projeto) throws ApiException {
        if (projeto.getPlanoExecucao() != null) {
            throw new ApiException("O plano de execucao ja foi cadastrado para este projeto.");
        }
    }

    private void validarEtapa6(Projeto projeto) throws ApiException {
        if (Boolean.TRUE.equals(projeto.getDeclarouVeracidadeInformacoes())) {
            throw new ApiException("Os termos de aceite ja foram preenchidos para este projeto.");
        }
    }

    private User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
