package portal.editais.service.projeto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.projeto.etapas.ProjetoEtapa1DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa2DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa3DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa4DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa5DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa6DTO;
import portal.editais.entity.Atividade;
import portal.editais.entity.Instituicao;
import portal.editais.entity.Localizacao;
import portal.editais.entity.Municipio;
import portal.editais.entity.PlanoExecucao;
import portal.editais.entity.PublicoBeneficiado;
import portal.editais.entity.Projeto;
import portal.editais.entity.User;
import portal.editais.repository.MunicipioRepository;
import portal.editais.repository.ProjetoRepository;
import portal.editais.service.instituicao.InstituicaoService;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private ProjetoRepository repository;
    private InstituicaoService instituicaoService;
    private MunicipioRepository municipioRepository;
    private final ProjetoRepository projetoRepository;
    private final EvidenciaRepository evidenciaRepository;
    private final DocumentoRepository documentoRepository;
    private final AtividadeProjetoRepository atividadeRepository;

    protected ProjetoServiceImpl(ProjetoRepository repository, InstituicaoService instituicaoService,
            MunicipioRepository municipioRepository, EvidenciaRepository evidenciaRepository,
                                 DocumentoRepository documentoRepository,
                                 AtividadeProjetoRepository atividadeRepository) {
        this.repository = repository;
        this.instituicaoService = instituicaoService;
        this.municipioRepository = municipioRepository;
        this.evidenciaRepository = evidenciaRepository;
        this.documentoRepository = documentoRepository;
        this.atividadeRepository = atividadeRepository;
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

        projeto.setNomeProjeto(dto.nomeProjeto());
        projeto.setEdital(dto.edital());
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
            throw new ApiException("Município não encontrado");
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

        return repository.save(projeto);
    }

    @Override
    public Projeto findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Projeto não encontrado: " + id));
    }

    private void validateAutor(Integer id) throws ApiException {
        if (!getLoggedInUser().getId().equals(id)) {
            throw new ApiException("Ação restrita ao dono do registro.");
        }
    }

    private void validarEtapa2(Projeto projeto) throws ApiException {
        if (projeto.getNomeProjeto() != null) {
            throw new ApiException("A etapa 2 já foi preenchida para este projeto.");
        }
    }

    private void validarEtapa3(Projeto projeto) throws ApiException {
        if (projeto.getLocalizacao() != null) {
            throw new ApiException("A localização já foi cadastrada para este projeto.");
        }
    }

    private void validarEtapa4(Projeto projeto) throws ApiException {
        if (projeto.getPublicoBeneficiado() != null) {
            throw new ApiException("O público beneficiado já foi cadastrado para este projeto.");
        }
    }

    private void validarEtapa5(Projeto projeto) throws ApiException {
        if (projeto.getPlanoExecucao() != null) {
            throw new ApiException("O plano de execução já foi cadastrado para este projeto.");
        }
    }

    private void validarEtapa6(Projeto projeto) throws ApiException {
        if (Boolean.TRUE.equals(projeto.getDeclarouVeracidadeInformacoes())) {
            throw new ApiException("Os termos de aceite já foram preenchidos para este projeto.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetoResponseDTO> listarProjetosDoProponente() {
        return projetoRepository.findBySubprojetoAutorId(getLoggedInUser().getId()).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetoResponseDTO> listarProjetosDoAuditor() {
        return projetoRepository.findByAuditorId(getLoggedInUser().getId()).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProjetoResponseDTO buscarProjeto(Integer id) {
        Projeto projeto = buscarProjetoPermitido(id);
        return toResponse(projeto);
    }

    @Override
    @Transactional
    public EvidenciaResponseDTO enviarEvidencia(Integer projetoId, EvidenciaDTO dto) {
        Projeto projeto = buscarProjetoPermitido(projetoId);
        if (!projeto.getSubprojeto().getAutor().getId().equals(getLoggedInUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Somente o proponente pode enviar evidencias.");
        }

        Documento foto = documentoRepository.findById(dto.fotoDocumentoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento da foto nao encontrado."));
        if (foto.getContexto() != ContextoDocumento.EVIDENCIA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento deve possuir contexto EVIDENCIA.");
        }

        AtividadeProjeto atividade = null;
        if (dto.atividadeId() != null) {
            atividade = atividadeRepository.findById(dto.atividadeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atividade nao encontrada."));
            if (!atividade.getProjeto().getId().equals(projetoId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atividade nao pertence ao projeto.");
            }
        }

        Evidencia evidencia = new Evidencia();
        evidencia.setProjeto(projeto);
        evidencia.setAtividade(atividade);
        evidencia.setFoto(foto);
        evidencia.setDescricao(dto.descricao());
        evidencia.setLatitude(dto.latitude());
        evidencia.setLongitude(dto.longitude());
        evidencia.setStatus(StatusEvidencia.PENDENTE);
        return EvidenciaResponseDTO.toResponse(evidenciaRepository.save(evidencia));
    }

    @Override
    @Transactional
    public EvidenciaResponseDTO validarEvidencia(Integer evidenciaId, ValidarEvidenciaDTO dto) {
        Evidencia evidencia = evidenciaRepository.findById(evidenciaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidencia nao encontrada."));
        if (!evidencia.getProjeto().getAuditor().getId().equals(getLoggedInUser().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Somente o auditor vinculado pode validar evidencias.");
        }
        if (dto.status() == StatusEvidencia.PENDENTE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Use APROVADA ou REJEITADA para validar evidencias.");
        }
        evidencia.setStatus(dto.status());
        evidencia.setComentarioAuditor(dto.comentario());
        evidencia.setValidadoPor(getLoggedInUser());
        return EvidenciaResponseDTO.toResponse(evidenciaRepository.save(evidencia));
    }

    private Projeto buscarProjetoPermitido(Integer id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto nao encontrado."));
        User usuario = getLoggedInUser();
        boolean proponente = projeto.getSubprojeto().getAutor().getId().equals(usuario.getId());
        boolean auditor = projeto.getAuditor().getId().equals(usuario.getId());
        if (!proponente && !auditor) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario nao vinculado ao projeto.");
        }
        return projeto;
    }

    private User getLoggedInUser() throws ApiException {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
