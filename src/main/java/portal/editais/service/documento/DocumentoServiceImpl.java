package portal.editais.service.documento;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portal.editais.dto.documento.DocumentoResponseDTO;
import portal.editais.entity.Documento;
import portal.editais.enumeration.ContextoDocumento;
import portal.editais.repository.DocumentoRepository;
import portal.editais.service.storage.ArquivoArmazenado;
import portal.editais.service.storage.StorageService;
import java.util.UUID;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final StorageService storageService;
    private final DocumentoRepository documentoRepository;

    public DocumentoServiceImpl(StorageService storageService, DocumentoRepository documentoRepository) {
        this.storageService = storageService;
        this.documentoRepository = documentoRepository;
    }

    @Override
    @Transactional
    public Documento salvarDocumento(MultipartFile arquivo, ContextoDocumento contexto, String donoId) {
        ArquivoArmazenado arquivoArmazenado = storageService.salvar(arquivo, contexto.name().toLowerCase(), donoId);
        Documento documento = Documento.builder()
            .nomeOriginal(arquivoArmazenado.nomeOriginal())
            .tipoConteudo(arquivoArmazenado.tipoConteudo())
            .tamanhoBytes(arquivoArmazenado.tamanhoBytes())
            .bucket(arquivoArmazenado.bucket())
            .objectKey(arquivoArmazenado.objectKey())
            .url(arquivoArmazenado.url())
            .contexto(contexto)
            .build();

        return documentoRepository.save(documento);
    }

    @Override
    @Transactional
    public DocumentoResponseDTO enviarDocumento(MultipartFile arquivo, ContextoDocumento contexto) {
        Documento documento = salvarDocumento(arquivo, contexto, UUID.randomUUID().toString());
        return new DocumentoResponseDTO(
            documento.getId(),
            documento.getNomeOriginal(),
            documento.getTipoConteudo(),
            documento.getTamanhoBytes(),
            documento.getBucket(),
            documento.getObjectKey(),
            documento.getUrl(),
            documento.getContexto(),
            documento.getCriadoEm()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentoResponseDTO buscarDocumentoPorId(UUID id) {
        Documento documento = documentoRepository.findById(id)
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND,
                "Documento não encontrado."
            ));

        return new DocumentoResponseDTO(
            documento.getId(),
            documento.getNomeOriginal(),
            documento.getTipoConteudo(),
            documento.getTamanhoBytes(),
            documento.getBucket(),
            documento.getObjectKey(),
            documento.getUrl(),
            documento.getContexto(),
            documento.getCriadoEm()
        );
    }
}
