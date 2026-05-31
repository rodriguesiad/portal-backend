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
import portal.editais.entity.Edital;
import portal.editais.entity.Instituicao;
import portal.editais.entity.Localizacao;
import portal.editais.entity.Municipio;
import portal.editais.entity.PlanoExecucao;
import portal.editais.entity.Projeto;
import portal.editais.entity.PublicoBeneficiado;
import portal.editais.entity.User;
import portal.editais.enumeration.SituacaoProjeto;
import portal.editais.repository.EditalRepository;
import portal.editais.repository.MunicipioRepository;
import portal.editais.repository.ProjetoRepository;
import portal.editais.service.instituicao.InstituicaoService;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private ProjetoRepository repository;
    private InstituicaoService instituicaoService;
    private MunicipioRepository municipioRepository;
    private EditalRepository editalRepository;

    protected ProjetoServiceImpl(ProjetoRepository repository, InstituicaoService instituicaoService,
            MunicipioRepository municipioRepository, EditalRepository editalRepository) {
        this.repository = repository;
        this.instituicaoService = instituicaoService;
        this.municipioRepository = municipioRepository;
        this.editalRepository = editalRepository;
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa1(ProjetoEtapa1DTO dto) throws ApiException {
        Projeto projeto = new Projeto();
        Instituicao instituicao = instituicaoService.create(dto.instituicao());

        projeto.setInstituicao(instituicao);
        projeto.setAutor(getLoggedInUser());
        projeto.setSituacao(SituacaoProjeto.RASCUNHO);

        return repository.save(projeto);
    }

    @Override
    @Transactional
    public Projeto implementaProjetoEtapa2(Integer id, ProjetoEtapa2DTO dto) throws ApiException {
        Projeto projeto = findById(id);
        validateAutor(projeto.getAutor().getId());
        validarEtapa2(projeto);

        Optional<Edital> edital = editalRepository.findById(dto.idEdital());

        if (edital.isEmpty()) {
            throw new ApiException("Edital não encontrado");
        }

        projeto.setNomeProjeto(dto.nomeProjeto());
        projeto.setEdital(edital.get());
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
        projeto.setSituacao(SituacaoProjeto.EM_ANALISE);

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

    private User getLoggedInUser() throws ApiException {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
