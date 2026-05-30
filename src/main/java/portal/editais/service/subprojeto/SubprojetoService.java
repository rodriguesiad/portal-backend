package portal.editais.service.subprojeto;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.subprojeto.SubprojetoEtapa1DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa2DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa3DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa4DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa5DTO;
import portal.editais.entity.Subprojeto;

public interface SubprojetoService {
    Subprojeto implementaSubprojetoEtapa1(SubprojetoEtapa1DTO dto) throws ApiException;

    Subprojeto implementaSubprojetoEtapa2(Integer id, SubprojetoEtapa2DTO dto) throws ApiException;

    Subprojeto implementaSubprojetoEtapa3(Integer id, SubprojetoEtapa3DTO dto) throws ApiException;

    Subprojeto implementaSubprojetoEtapa4(Integer id, SubprojetoEtapa4DTO dto) throws ApiException;

    Subprojeto implementaSubprojetoEtapa5(Integer id, SubprojetoEtapa5DTO dto) throws ApiException;

    Subprojeto findById(Integer id) throws ApiException;
}
