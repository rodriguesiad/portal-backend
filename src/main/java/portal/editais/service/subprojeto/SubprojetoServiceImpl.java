package portal.editais.service.subprojeto;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.subprojeto.SubprojetoEtapa1DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa2DTO;
import portal.editais.entity.AtividadeSubprojeto;
import portal.editais.entity.Edital;
import portal.editais.entity.Instituicao;
import portal.editais.entity.Subprojeto;
import portal.editais.entity.User;
import portal.editais.enumeration.StatusEdital;
import portal.editais.enumeration.StatusSubprojeto;
import portal.editais.repository.EditalRepository;
import portal.editais.repository.SubprojetoRepository;
import portal.editais.service.instituicao.InstituicaoService;

@Service
public class SubprojetoServiceImpl implements SubprojetoService {

    private SubprojetoRepository repository;
    private InstituicaoService instituicaoService;
    private EditalRepository editalRepository;

    protected SubprojetoServiceImpl(
            SubprojetoRepository repository,
            InstituicaoService instituicaoService,
            EditalRepository editalRepository) {
        this.repository = repository;
        this.instituicaoService = instituicaoService;
        this.editalRepository = editalRepository;
    }

    @Override
    @Transactional
    public Subprojeto implementaSubprojetoEtapa1(SubprojetoEtapa1DTO dto) throws ApiException {
        Subprojeto subprojeto = new Subprojeto();
        Instituicao instituicao = instituicaoService.create(dto.instituicao());

        subprojeto.setInstituicao(instituicao);
        subprojeto.setAutor(getLoggedInUser());

        return repository.save(subprojeto);
    }

    @Override
    @Transactional
    public Subprojeto implementaSubprojetoEtapa2(Integer id, SubprojetoEtapa2DTO dto) throws ApiException {
        Subprojeto subprojeto = findById(id);
        this.validateAutor(subprojeto.getAutor().getId());
        Edital edital = editalRepository.findById(dto.edital())
                .orElseThrow(() -> new ApiException("Edital não encontrado: " + dto.edital()));
        if (edital.getStatus() != StatusEdital.ABERTO) {
            throw new ApiException("Somente editais abertos recebem submissões.");
        }

        subprojeto.setNomeSubprojeto(dto.nomeSubprojeto());
        subprojeto.setEdital(edital);
        subprojeto.setResumo(dto.resumo());
        subprojeto.setJustificativaMerito(dto.justificativaMerito());
        subprojeto.setStatus(StatusSubprojeto.SUBMETIDO);
        subprojeto.getAtividades().clear();
        if (dto.atividades() != null) {
            dto.atividades().forEach(item -> {
                AtividadeSubprojeto atividade = new AtividadeSubprojeto();
                atividade.setSubprojeto(subprojeto);
                atividade.setNome(item.nome());
                atividade.setResponsavel(item.responsavel());
                atividade.setInicio(item.inicio());
                atividade.setFim(item.fim());
                atividade.setDescricao(item.descricao());
                subprojeto.getAtividades().add(atividade);
            });
        }

        return repository.save(subprojeto);
    }

    @Override
    public Subprojeto findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Subprojeto não encontrado: " + id));
    }

    private void validateAutor(Integer id) throws ApiException {
        if (!getLoggedInUser().getId().equals(id)) {
            throw new ApiException("Ação restrita ao dono do registro.");
        }
    }

    private User getLoggedInUser() throws ApiException {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
