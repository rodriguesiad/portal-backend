package portal.editais.service.projeto;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.projeto.etapas.ProjetoEtapa1DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa2DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa3DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa4DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa5DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa6DTO;
import portal.editais.entity.Projeto;
import java.util.List;
import portal.editais.dto.projeto.EvidenciaDTO;
import portal.editais.dto.projeto.EvidenciaResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.dto.projeto.ValidarEvidenciaDTO;

public interface ProjetoService {
    Projeto implementaProjetoEtapa1(ProjetoEtapa1DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa2(Integer id, ProjetoEtapa2DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa3(Integer id, ProjetoEtapa3DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa4(Integer id, ProjetoEtapa4DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa5(Integer id, ProjetoEtapa5DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa6(Integer id, ProjetoEtapa6DTO dto) throws ApiException;

    Projeto findById(Integer id) throws ApiException;

    List<ProjetoResponseDTO> listarProjetosDoProponente();

    List<ProjetoResponseDTO> listarProjetosDoAuditor();

    ProjetoResponseDTO buscarProjeto(Integer id);

    EvidenciaResponseDTO enviarEvidencia(Integer projetoId, EvidenciaDTO dto);

    EvidenciaResponseDTO validarEvidencia(Integer evidenciaId, ValidarEvidenciaDTO dto);
}
