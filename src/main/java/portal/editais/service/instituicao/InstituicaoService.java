package portal.editais.service.instituicao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.projeto.instituicao.InstituicaoDTO;
import portal.editais.entity.Instituicao;

public interface InstituicaoService {
    Instituicao create(InstituicaoDTO dto) throws ApiException;

    Instituicao findById(Integer id) throws ApiException;

    Page<Instituicao> findAll(Pageable pageable) throws ApiException;
}
