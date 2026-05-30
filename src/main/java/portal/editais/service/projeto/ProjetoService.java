package portal.editais.service.projeto;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.projeto.etapas.ProjetoEtapa1DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa2DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa3DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa4DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa5DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa6DTO;
import portal.editais.entity.Projeto;

public interface ProjetoService {
    Projeto implementaProjetoEtapa1(ProjetoEtapa1DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa2(Integer id, ProjetoEtapa2DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa3(Integer id, ProjetoEtapa3DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa4(Integer id, ProjetoEtapa4DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa5(Integer id, ProjetoEtapa5DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa6(Integer id, ProjetoEtapa6DTO dto) throws ApiException;

    Projeto findById(Integer id) throws ApiException;
}
