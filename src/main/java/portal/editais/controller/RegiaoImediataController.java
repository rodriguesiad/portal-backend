package portal.editais.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import portal.editais.dto.edital.RegiaoImediataResponseDTO;
import portal.editais.service.regiaoimediata.RegiaoImediataService;

@RestController
@RequestMapping("/regioes-imediatas")
@Tag(name = "Regiões Imediatas")
public class RegiaoImediataController {

    private final RegiaoImediataService regiaoImediataService;

    public RegiaoImediataController(RegiaoImediataService regiaoImediataService) {
        this.regiaoImediataService = regiaoImediataService;
    }

    @GetMapping
    @Operation(summary = "Listar regiões imediatas ativas")
    public ResponseEntity<List<RegiaoImediataResponseDTO>> listarRegioesImediatasAtivas() {
        return ResponseEntity.ok(regiaoImediataService.listarRegioesImediatasAtivas().stream()
                .map(RegiaoImediataResponseDTO::toResponse).toList());
    }
}
