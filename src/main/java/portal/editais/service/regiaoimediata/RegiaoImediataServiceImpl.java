package portal.editais.service.regiaoimediata;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.editais.dto.regiaoimediata.RegiaoImediataResponseDTO;
import portal.editais.entity.RegiaoImediata;
import portal.editais.repository.RegiaoImediataRepository;

@Service
public class RegiaoImediataServiceImpl implements RegiaoImediataService {

    private final RegiaoImediataRepository regiaoImediataRepository;

    public RegiaoImediataServiceImpl(RegiaoImediataRepository regiaoImediataRepository) {
        this.regiaoImediataRepository = regiaoImediataRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegiaoImediataResponseDTO> listarRegioesImediatasAtivas() {
        return regiaoImediataRepository.findByAtivoTrueOrderByNomeAsc()
            .stream()
            .map(RegiaoImediataResponseDTO::toResponse)
            .toList();
    }


}
