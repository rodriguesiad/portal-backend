package portal.editais.service.natureza.juridica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import portal.editais.config.exception.ApiException;
import portal.editais.entity.NaturezaJuridica;
import portal.editais.repository.NaturezaJuridicaRepository;

@Service
public class NaturezaJuridicaServiceImpl implements NaturezaJuridicaService {

    private NaturezaJuridicaRepository repository;

    protected NaturezaJuridicaServiceImpl(NaturezaJuridicaRepository repository) {
        this.repository = repository;
    }

    @Override
    public NaturezaJuridica findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Registro não encontrado: " + id));
    }

    @Override
    public Page<NaturezaJuridica> findAll(Pageable pageable) throws ApiException {
        return repository.findAll(pageable);
    }

}
