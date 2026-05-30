package portal.editais.service.natureza.juridica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import portal.editais.config.exception.ApiException;
import portal.editais.entity.NaturezaJuridica;

public interface NaturezaJuridicaService {
    NaturezaJuridica findById(Integer id) throws ApiException;

    Page<NaturezaJuridica> findAll(Pageable pageable) throws ApiException;
}
