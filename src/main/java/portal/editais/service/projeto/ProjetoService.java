package portal.editais.service.projeto;

import java.util.List;
import portal.editais.dto.projeto.EvidenciaDTO;
import portal.editais.dto.projeto.EvidenciaResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.dto.projeto.ValidarEvidenciaDTO;

public interface ProjetoService {
    List<ProjetoResponseDTO> listarProjetosDoProponente();

    List<ProjetoResponseDTO> listarProjetosDoAuditor();

    ProjetoResponseDTO buscarProjeto(Integer id);

    EvidenciaResponseDTO enviarEvidencia(Integer projetoId, EvidenciaDTO dto);

    EvidenciaResponseDTO validarEvidencia(Integer evidenciaId, ValidarEvidenciaDTO dto);
}
