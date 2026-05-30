package portal.editais.service.subprojeto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.subprojeto.SubprojetoDTO;
import portal.editais.dto.subprojeto.instituicao.InstituicaoDocumentosDTO;
import portal.editais.entity.Instituicao;
import portal.editais.entity.NaturezaJuridica;
import portal.editais.entity.RepresentanteLegal;
import portal.editais.entity.Subprojeto;
import portal.editais.entity.User;
import portal.editais.repository.SubprojetoRepository;
import portal.editais.service.instituicao.InstituicaoService;

@Service
public class SubprojetoServiceImpl implements SubprojetoService {

    private SubprojetoRepository repository;
    private InstituicaoService instituicaoService;

    protected SubprojetoServiceImpl(SubprojetoRepository repository,InstituicaoService instituicaoService) {
        this.repository = repository;
        this.instituicaoService = instituicaoService;
    }

    @Override
    @Transactional
    public Subprojeto createSubprojeto(SubprojetoDTO dto, InstituicaoDocumentosDTO documentosDTO) throws ApiException {
        Subprojeto subprojeto = new Subprojeto();
        Instituicao instituicao = instituicaoService.create(dto.instituicao());

        subprojeto.setInstituicao(instituicao);
        subprojeto.setAutor(getLoggedInUser());

        return repository.save(subprojeto);
    }

    private User getLoggedInUser() throws ApiException {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
