package portal.editais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import portal.editais.dto.subprojeto.SubprojetoDTO;
import portal.editais.dto.subprojeto.SubprojetoResponseDTO;
import portal.editais.entity.Subprojeto;
import portal.editais.service.subprojeto.SubprojetoService;

@RestController
@RequestMapping("/subprojetos")
public class SubprojetoController {

    private SubprojetoService service;

    public SubprojetoController(SubprojetoService service) {
        this.service = service;
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-1")
    @Operation(description = "Inserindo etapa 1 da submissão de projetos")
    public ResponseEntity<SubprojetoResponseDTO> createIdentificacaoProponente(
            @Valid @RequestBody SubprojetoDTO dto) throws Exception {

        Subprojeto entity = service.createSubprojeto(dto, null);
        return ResponseEntity.ok(SubprojetoResponseDTO.toResponse(entity));
    }

}
