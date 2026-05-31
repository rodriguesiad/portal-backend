package portal.editais.service.projeto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.projeto.ProjetoFilterDTO;
import portal.editais.dto.projeto.ProjetoIndicadoresDTO;
import portal.editais.dto.projeto.EvidenciaDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.dto.projeto.ValidarEvidenciaDTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa1DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa2DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa3DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa4DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa5DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa6DTO;
import portal.editais.entity.Projeto;
import java.util.List;

public interface ProjetoService {
    Projeto implementaProjetoEtapa1(ProjetoEtapa1DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa2(Integer id, ProjetoEtapa2DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa3(Integer id, ProjetoEtapa3DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa4(Integer id, ProjetoEtapa4DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa5(Integer id, ProjetoEtapa5DTO dto) throws ApiException;

    Projeto implementaProjetoEtapa6(Integer id, ProjetoEtapa6DTO dto) throws ApiException;

    Projeto findById(Integer id) throws ApiException;

    Page<Projeto> listarProjetosDoAutor(Pageable pageable, ProjetoFilterDTO filterRequest) throws ApiException;

    Page<Projeto> listarTodosProjetos(Pageable pageable, ProjetoFilterDTO filterRequest) throws ApiException;

    ProjetoIndicadoresDTO obterIndicadoresDoAutor() throws ApiException;

    ProjetoIndicadoresDTO obterIndicadoresGerais() throws ApiException;

    List<ProjetoResponseDTO> listarProjetosDoProponente();

    List<ProjetoResponseDTO> listarProjetosDoAuditor();

    ProjetoResponseDTO buscarProjetoResponse(Integer id);

    ProjetoResponseDTO iniciarAvaliacao(Integer id);

    ProjetoResponseDTO criarEvidencia(Integer id, EvidenciaDTO dto);

    ProjetoResponseDTO validarEvidencia(Integer evidenciaId, ValidarEvidenciaDTO dto);
}
