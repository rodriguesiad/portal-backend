package portal.editais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import portal.editais.dto.documento.DocumentoDownloadDTO;
import portal.editais.dto.documento.DocumentoResponseDTO;
import portal.editais.enumeration.ContextoDocumento;
import portal.editais.service.documento.DocumentoService;

@RestController
@RequestMapping("/documentos")
@Tag(name = "Documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Enviar documento para o MinIO")
    public ResponseEntity<DocumentoResponseDTO> enviarDocumento(
        @RequestPart("arquivo") MultipartFile arquivo,
        @RequestParam ContextoDocumento contexto
    ) {
        return ResponseEntity.ok(documentoService.enviarDocumento(arquivo, contexto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Baixar documento por UUID")
    public ResponseEntity<byte[]> baixarDocumento(@PathVariable UUID id) {
        DocumentoDownloadDTO documento = documentoService.baixarDocumento(id);
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(documento.tipoConteudo()))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                ContentDisposition.attachment()
                    .filename(documento.nomeOriginal(), StandardCharsets.UTF_8)
                    .build()
                    .toString()
            )
            .body(documento.conteudo());
    }
}
