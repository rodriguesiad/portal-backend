package portal.editais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import portal.editais.dto.documento.DocumentoEditalResponseDTO;
import portal.editais.dto.documento.VincularDocumentosDTO;
import portal.editais.dto.edital.*;
import portal.editais.enumeration.SituacaoEdital;
import portal.editais.enumeration.StatusEdital;
import portal.editais.service.edital.EditalService;

@RestController
@RequestMapping
@Tag(name = "Editais")
public class EditalController {

    private final EditalService editalService;

    public EditalController(EditalService editalService) {
        this.editalService = editalService;
    }

    @PostMapping("/editais")
    @Operation(summary = "Criar edital vinculando documentos já enviados")
    public ResponseEntity<EditalResponseDTO> criarEdital(@RequestBody @Valid EditalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(editalService.criarEdital(dto));
    }

    @PutMapping("/editais/{id}")
    @Operation(summary = "Atualizar edital em rascunho")
    public ResponseEntity<EditalResponseDTO> atualizarEdital(
        @PathVariable Integer id,
        @RequestBody @Valid EditalUpdateDTO dto
    ) {
        return ResponseEntity.ok(editalService.atualizarEdital(id, dto));
    }

    @PostMapping("/editais/{id}/documentos")
    @Operation(summary = "Vincular documentos já enviados ao edital")
    public ResponseEntity<List<DocumentoEditalResponseDTO>> vincularDocumentos(
        @PathVariable Integer id,
        @RequestBody @Valid VincularDocumentosDTO dto
    ) {
        return ResponseEntity.ok(editalService.vincularDocumentos(id, dto.documentosIds()));
    }

    @GetMapping("/editais")
    @Operation(summary = "Listar editais públicos")
    public ResponseEntity<Page<EditalResumoResponseDTO>> listarEditaisPublicos(
        @RequestParam(required = false) SituacaoEdital situacao,
        @RequestParam(required = false) Integer frenteAtuacaoId,
        @RequestParam(required = false) Integer regiaoImediataId,
        @RequestParam(required = false) Integer orgaoProponenteId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioRecebimento,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fimRecebimento,
        @PageableDefault(size = 10, sort = "inicioRecebimentoPropostas", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(editalService.listarEditaisPublicos(
            situacao,
            frenteAtuacaoId,
            regiaoImediataId,
            orgaoProponenteId,
            inicioRecebimento,
            fimRecebimento,
            pageable
        ));
    }

    @GetMapping("/editais/{id}")
    @Operation(summary = "Buscar edital por id")
    public ResponseEntity<EditalResponseDTO> buscarEditalPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(editalService.buscarEditalPorId(id));
    }

    @GetMapping("/admin/editais")
    @Operation(summary = "Listar editais administrativos")
    public ResponseEntity<Page<EditalResumoResponseDTO>> listarEditaisAdministrativos(
        @RequestParam(required = false) StatusEdital status,
        @RequestParam(required = false) Integer frenteAtuacaoId,
        @RequestParam(required = false) Integer regiaoImediataId,
        @RequestParam(required = false) Integer orgaoProponenteId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioRecebimento,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fimRecebimento,
        @PageableDefault(size = 10, sort = "criadoEm", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(editalService.listarEditaisAdministrativos(
            status,
            frenteAtuacaoId,
            regiaoImediataId,
            orgaoProponenteId,
            inicioRecebimento,
            fimRecebimento,
            pageable
        ));
    }
}
