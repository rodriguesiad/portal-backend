package portal.editais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

    @org.springframework.web.bind.annotation.GetMapping("/{id}")
    @Operation(summary = "Buscar documento por UUID")
    public ResponseEntity<DocumentoResponseDTO> buscarDocumentoPorId(@org.springframework.web.bind.annotation.PathVariable UUID id) {
        return ResponseEntity.ok(documentoService.buscarDocumentoPorId(id));
    }
}
