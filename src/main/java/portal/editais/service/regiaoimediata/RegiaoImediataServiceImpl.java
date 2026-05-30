package portal.editais.service.regiaoimediata;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<RegiaoImediata> listarRegioesImediatasAtivas() {
        return regiaoImediataRepository.findByAtivoTrueOrderByNomeAsc();
    }

}
