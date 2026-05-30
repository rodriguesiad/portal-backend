package portal.editais.service.subprojeto;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.subprojeto.SubprojetoDTO;
import portal.editais.dto.subprojeto.instituicao.InstituicaoDocumentosDTO;
import portal.editais.entity.Subprojeto;

public interface SubprojetoService {
    Subprojeto createSubprojeto(SubprojetoDTO dto, InstituicaoDocumentosDTO documentosDTO) throws ApiException;
}
