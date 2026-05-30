package portal.editais.service.regiaoimediata;

import java.util.List;
import portal.editais.dto.edital.RegiaoImediataResponseDTO;

public interface RegiaoImediataService {
    List<RegiaoImediataResponseDTO> listarRegioesImediatasAtivas();
}
