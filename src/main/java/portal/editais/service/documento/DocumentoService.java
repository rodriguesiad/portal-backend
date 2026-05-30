package portal.editais.service.documento;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import portal.editais.dto.documento.DocumentoResponseDTO;
import portal.editais.entity.Documento;
import portal.editais.enumeration.ContextoDocumento;

public interface DocumentoService {
    Documento salvarDocumento(MultipartFile arquivo, ContextoDocumento contexto, String donoId);

    DocumentoResponseDTO enviarDocumento(MultipartFile arquivo, ContextoDocumento contexto);

    DocumentoResponseDTO buscarDocumentoPorId(UUID id);
}
