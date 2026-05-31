package portal.editais.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.dto.projeto.ValidarEvidenciaDTO;
import portal.editais.service.projeto.ProjetoService;

@RestController
@Tag(name = "Acompanhamento de Projetos")
public class ProjetoAcompanhamentoController {

    private final ProjetoService service;

    public ProjetoAcompanhamentoController(ProjetoService service) {
        this.service = service;
    }

    @Secured({ "ROLE_PROPONENTE" })
    @GetMapping("/proponente/projetos")
    public ResponseEntity<List<ProjetoResponseDTO>> listarProjetosDoProponente() {
        return ResponseEntity.ok(service.listarProjetosDoProponente());
    }

    @Secured({ "ROLE_AUDITOR" })
    @GetMapping("/auditor/projetos")
    public ResponseEntity<List<ProjetoResponseDTO>> listarProjetosDoAuditor() {
        return ResponseEntity.ok(service.listarProjetosDoAuditor());
    }

    @Secured({ "ROLE_AUDITOR" })
    @PostMapping("/auditor/evidencias/{id}/validar")
    public ResponseEntity<ProjetoResponseDTO> validarEvidencia(
            @PathVariable Integer id,
            @Valid @RequestBody ValidarEvidenciaDTO dto) {
        return ResponseEntity.ok(service.validarEvidencia(id, dto));
    }
}
