package portal.editais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portal.editais.dto.orgao.OrgaoProponenteResponseDTO;
import portal.editais.service.orgaoproponente.OrgaoProponenteService;

@RestController
@RequestMapping("/orgaos-proponentes")
@Tag(name = "Órgãos Proponentes")
public class OrgaoProponenteController {

    private final OrgaoProponenteService orgaoProponenteService;

    public OrgaoProponenteController(OrgaoProponenteService orgaoProponenteService) {
        this.orgaoProponenteService = orgaoProponenteService;
    }

    @GetMapping
    @Operation(summary = "Listar órgãos proponentes ativos do Tocantins")
    public ResponseEntity<List<OrgaoProponenteResponseDTO>> listarOrgaosProponentesAtivos() {
        return ResponseEntity.ok(orgaoProponenteService.listarOrgaosProponentesAtivos());
    }
}
