package portal.editais.service.municipio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import portal.editais.config.exception.ApiException;
import portal.editais.entity.Municipio;
import portal.editais.repository.MunicipioRepository;

@Service
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioRepository repository;

    public MunicipioServiceImpl(MunicipioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Municipio findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Registro não encontrado: " + id));
    }

    @Override
    public Page<Municipio> findAll(Pageable pageable) throws ApiException {
        return repository.findAll(pageable);
    }
}
