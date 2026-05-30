package portal.editais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import portal.editais.dto.subprojeto.SubprojetoEtapa1DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa2DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa3DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa4DTO;
import portal.editais.dto.subprojeto.SubprojetoEtapa5DTO;
import portal.editais.dto.subprojeto.SubprojetoResponseEtapa1DTO;
import portal.editais.dto.subprojeto.SubprojetoResponseEtapa2DTO;
import portal.editais.dto.subprojeto.SubprojetoResponseEtapa3DTO;
import portal.editais.dto.subprojeto.SubprojetoResponseEtapa4DTO;
import portal.editais.dto.subprojeto.SubprojetoResponseEtapa5DTO;
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
    @Operation(description = "Inserindo etapa 1 da submissão de subprojeto")
    public ResponseEntity<SubprojetoResponseEtapa1DTO> createIdentificacaoProponente(
            @Valid @RequestBody SubprojetoEtapa1DTO dto) throws Exception {

        Subprojeto entity = service.implementaSubprojetoEtapa1(dto);
        return ResponseEntity.ok(SubprojetoResponseEtapa1DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-2/{id}")
    @Operation(description = "Inserindo etapa 2 da submissão de subprojeto")
    public ResponseEntity<SubprojetoResponseEtapa2DTO> createCaracterizarProjeto(
            @Valid @RequestBody SubprojetoEtapa2DTO dto, @PathVariable Integer id) throws Exception {

        Subprojeto entity = service.implementaSubprojetoEtapa2(id, dto);
        return ResponseEntity.ok(SubprojetoResponseEtapa2DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-3/{id}")
    @Operation(description = "Inserindo etapa 3 da submissão de subprojeto")
    public ResponseEntity<SubprojetoResponseEtapa3DTO> createLocalizacao(
            @Valid @RequestBody SubprojetoEtapa3DTO dto, @PathVariable Integer id) throws Exception {

        Subprojeto entity = service.implementaSubprojetoEtapa3(id, dto);
        return ResponseEntity.ok(SubprojetoResponseEtapa3DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-4/{id}")
    @Operation(description = "Inserindo etapa 4 da submissão de subprojeto")
    public ResponseEntity<SubprojetoResponseEtapa4DTO> createPublicoBeneficiado(
            @Valid @RequestBody SubprojetoEtapa4DTO dto, @PathVariable Integer id) throws Exception {

        Subprojeto entity = service.implementaSubprojetoEtapa4(id, dto);
        return ResponseEntity.ok(SubprojetoResponseEtapa4DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-5/{id}")
    @Operation(description = "Inserindo etapa 5 da submissão de subprojeto")
    public ResponseEntity<SubprojetoResponseEtapa5DTO> createPublicoBeneficiado(
            @Valid @RequestBody SubprojetoEtapa5DTO dto, @PathVariable Integer id) throws Exception {

        Subprojeto entity = service.implementaSubprojetoEtapa5(id, dto);
        return ResponseEntity.ok(SubprojetoResponseEtapa5DTO.toResponse(entity));
    }

}
