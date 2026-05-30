package portal.editais.service.orgaoproponente;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portal.editais.dto.orgao.OrgaoProponenteResponseDTO;
import portal.editais.repository.OrgaoProponenteRepository;

@Service
public class OrgaoProponenteServiceImpl implements OrgaoProponenteService {

    private static final String SIGLA_TOCANTINS = "TO";

    private final OrgaoProponenteRepository orgaoProponenteRepository;

    public OrgaoProponenteServiceImpl(OrgaoProponenteRepository orgaoProponenteRepository) {
        this.orgaoProponenteRepository = orgaoProponenteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgaoProponenteResponseDTO> listarOrgaosProponentesAtivos() {
        return orgaoProponenteRepository.findByEstadoSiglaAndAtivoTrueOrderByNomeAsc(SIGLA_TOCANTINS)
                .stream()
                .map(OrgaoProponenteResponseDTO::toResponse)
                .toList();
    }
}
