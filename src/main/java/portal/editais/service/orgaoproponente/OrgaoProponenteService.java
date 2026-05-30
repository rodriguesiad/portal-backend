package portal.editais.service.orgaoproponente;

import java.util.List;
import portal.editais.dto.orgao.OrgaoProponenteResponseDTO;

public interface OrgaoProponenteService {
    List<OrgaoProponenteResponseDTO> listarOrgaosProponentesAtivos();
}
