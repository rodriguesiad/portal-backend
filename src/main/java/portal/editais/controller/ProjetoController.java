package portal.editais.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portal.editais.dto.projeto.EvidenciaDTO;
import portal.editais.dto.projeto.EvidenciaResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.dto.projeto.ValidarEvidenciaDTO;
import portal.editais.service.projeto.ProjetoService;

@RestController
@RequestMapping
@Tag(name = "Projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @Secured("ROLE_PROPONENTE")
    @GetMapping("/proponente/projetos")
    public ResponseEntity<List<ProjetoResponseDTO>> listarProjetosProponente() {
        return ResponseEntity.ok(projetoService.listarProjetosDoProponente());
    }

    @Secured("ROLE_AUDITOR")
    @GetMapping("/auditor/projetos")
    public ResponseEntity<List<ProjetoResponseDTO>> listarProjetosAuditor() {
        return ResponseEntity.ok(projetoService.listarProjetosDoAuditor());
    }

    @GetMapping("/projetos/{id}")
    public ResponseEntity<ProjetoResponseDTO> buscarProjeto(@PathVariable Integer id) {
        return ResponseEntity.ok(projetoService.buscarProjeto(id));
    }

    @Secured("ROLE_PROPONENTE")
    @PostMapping("/projetos/{id}/evidencias")
    public ResponseEntity<EvidenciaResponseDTO> enviarEvidencia(
            @PathVariable Integer id,
            @RequestBody @Valid EvidenciaDTO dto) {
        return ResponseEntity.ok(projetoService.enviarEvidencia(id, dto));
    }

    @Secured("ROLE_AUDITOR")
    @PostMapping("/auditor/evidencias/{id}/validar")
    public ResponseEntity<EvidenciaResponseDTO> validarEvidencia(
            @PathVariable Integer id,
            @RequestBody @Valid ValidarEvidenciaDTO dto) {
        return ResponseEntity.ok(projetoService.validarEvidencia(id, dto));
    }
}
