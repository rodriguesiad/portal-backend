package portal.editais.service.subprojeto;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.subprojeto.SubprojetoEtapa1DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa2DTO;
import portal.editais.entity.Subprojeto;

public interface SubprojetoService {
    Subprojeto implementaSubprojetoEtapa1(SubprojetoEtapa1DTO dto) throws ApiException;

    Subprojeto implementaSubprojetoEtapa2(Integer id, SubprojetoEtapa2DTO dto) throws ApiException;

    Subprojeto findById(Integer id) throws ApiException;
}
