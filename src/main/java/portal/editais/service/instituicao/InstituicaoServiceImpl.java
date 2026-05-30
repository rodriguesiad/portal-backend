package portal.editais.service.instituicao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.instituicao.InstituicaoDTO;
import portal.editais.entity.Instituicao;
import portal.editais.entity.NaturezaJuridica;
import portal.editais.entity.RepresentanteLegal;
import portal.editais.repository.InstituicaoRepository;
import portal.editais.repository.NaturezaJuridicaRepository;

@Service
public class InstituicaoServiceImpl implements InstituicaoService {

    private InstituicaoRepository repository;
    private NaturezaJuridicaRepository naturezaJuridicaRepository;

    protected InstituicaoServiceImpl(InstituicaoRepository repository,
            NaturezaJuridicaRepository naturezaJuridicaRepository) {
        this.repository = repository;
        this.naturezaJuridicaRepository = naturezaJuridicaRepository;
    }

    @Override
    @Transactional
    public Instituicao create(InstituicaoDTO dto) throws ApiException {
        Optional<NaturezaJuridica> naturezaJuridica = naturezaJuridicaRepository.findById(dto.idNaturezaJuridica());

        Instituicao registro = new Instituicao();
        registro.setNomeFantasia(dto.nomeFantasia());
        registro.setRazaoSocial(dto.razaoSocial());
        registro.setCnpj(dto.cnpj());
        registro.setDataFundacao(dto.dataFundacao());
        registro.setNaturezaJuridica(naturezaJuridica.get());
        registro.setAreaAtuacao(dto.areaAtuacao());
        registro.setSite(dto.site());
        registro.setRedesSociais(dto.redesSociais());
        registro.setSituacao("Válido");

        RepresentanteLegal representante = new RepresentanteLegal();
        representante.setNomeCompleto(dto.representanteLegal().nomeCompleto());
        representante.setCpf(dto.representanteLegal().cpf());
        representante.setRg(dto.representanteLegal().rg());
        representante.setEmail(dto.representanteLegal().email());
        representante.setTelefone(dto.representanteLegal().telefone());
        representante.setCargo(dto.representanteLegal().cargo());

        representante.setInstituicao(registro);
        registro.setRepresentanteLegal(representante);

        return repository.save(registro);
    }

    @Override
    public Instituicao findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Instituição não encontrada: " + id));
    }

    @Override
    public Page<Instituicao> findAll(Pageable pageable) throws ApiException {
        return repository.findAll(pageable);
    }

}
