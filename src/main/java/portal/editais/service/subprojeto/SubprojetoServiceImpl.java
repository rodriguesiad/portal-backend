package portal.editais.service.subprojeto;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.subprojeto.SubprojetoEtapa1DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa2DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa3DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa4DTO;
import portal.editais.entity.Instituicao;
import portal.editais.entity.Localizacao;
import portal.editais.entity.Municipio;
import portal.editais.entity.PublicoBeneficiado;
import portal.editais.entity.Subprojeto;
import portal.editais.entity.User;
import portal.editais.repository.MunicipioRepository;
import portal.editais.repository.SubprojetoRepository;
import portal.editais.service.instituicao.InstituicaoService;

@Service
public class SubprojetoServiceImpl implements SubprojetoService {

    private SubprojetoRepository repository;
    private InstituicaoService instituicaoService;
    private MunicipioRepository municipioRepository;

    protected SubprojetoServiceImpl(SubprojetoRepository repository, InstituicaoService instituicaoService,
            MunicipioRepository municipioRepository) {
        this.repository = repository;
        this.instituicaoService = instituicaoService;
        this.municipioRepository = municipioRepository;
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

        subprojeto.setNomeSubprojeto(dto.nomeSubprojeto());
        subprojeto.setEdital(dto.edital());
        subprojeto.setResumo(dto.resumo());
        subprojeto.setJustificativaMerito(dto.justificativaMerito());

        return repository.save(subprojeto);
    }

    @Override
    @Transactional
    public Subprojeto implementaSubprojetoEtapa3(Integer id, SubprojetoEtapa3DTO dto) throws ApiException {
        Subprojeto subprojeto = findById(id);
        this.validateAutor(subprojeto.getAutor().getId());

        Optional<Municipio> municipio = municipioRepository.findById(dto.localizacao().idMunicipio());

        if (municipio.isEmpty()) {
            throw new ApiException("Município não encontrado");
        }

        Localizacao localizacao = new Localizacao();
        localizacao.setLatitude(dto.localizacao().latitude());
        localizacao.setLongitude(dto.localizacao().longitude());
        localizacao.setMunicipio(municipio.get());
        localizacao.setComunidade(dto.localizacao().comunidade());

        localizacao.setSubprojeto(subprojeto);
        subprojeto.setLocalizacao(localizacao);

        return repository.save(subprojeto);
    }

    @Override
    @Transactional
    public Subprojeto implementaSubprojetoEtapa4(Integer id, SubprojetoEtapa4DTO dto) throws ApiException {
        Subprojeto subprojeto = findById(id);
        this.validateAutor(subprojeto.getAutor().getId());

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

        publicoBeneficiado.setSubprojeto(subprojeto);
        subprojeto.setPublicoBeneficiado(publicoBeneficiado);

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
