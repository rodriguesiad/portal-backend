package portal.editais.service.regiaoimediata;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.editais.dto.edital.RegiaoImediataResponseDTO;
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
            .map(this::paraResponse)
            .toList();
    }

    private RegiaoImediataResponseDTO paraResponse(RegiaoImediata regiaoImediata) {
        return new RegiaoImediataResponseDTO(
            regiaoImediata.getId(),
            regiaoImediata.getCodigo(),
            regiaoImediata.getNome()
        );
    }
}
