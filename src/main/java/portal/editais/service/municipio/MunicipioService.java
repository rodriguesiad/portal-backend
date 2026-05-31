package portal.editais.service.municipio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.genai.errors.ApiException;

import portal.editais.entity.Municipio;

public interface MunicipioService {
    Municipio findById(Integer id) throws ApiException;

    Page<Municipio> findAll(Pageable pageable) throws ApiException;
}
