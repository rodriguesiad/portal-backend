package portal.editais.service.edital;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import portal.editais.dto.documento.DocumentoEditalResponseDTO;
import portal.editais.dto.edital.*;
import portal.editais.enumeration.SituacaoEdital;
import portal.editais.enumeration.StatusEdital;

public interface EditalService {
    EditalResponseDTO criarEdital(EditalDTO dto);

    EditalResponseDTO atualizarEdital(Integer id, EditalUpdateDTO dto);

    List<DocumentoEditalResponseDTO> vincularDocumentos(Integer id, List<UUID> documentosIds);

    EditalResponseDTO buscarEditalPorId(Integer id);

    Page<EditalResumoResponseDTO> listarEditaisPublicos(
        SituacaoEdital situacao,
        Integer frenteAtuacaoId,
        Integer regiaoImediataId,
        Integer orgaoProponenteId,
        LocalDate inicioRecebimento,
        LocalDate fimRecebimento,
        Pageable pageable
    );

    Page<EditalResumoResponseDTO> listarEditaisAdministrativos(
        StatusEdital status,
        Integer frenteAtuacaoId,
        Integer regiaoImediataId,
        Integer orgaoProponenteId,
        LocalDate inicioRecebimento,
        LocalDate fimRecebimento,
        Pageable pageable
    );

    void atualizarStatusPorDatas();
}
