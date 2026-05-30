package portal.editais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import portal.editais.dto.projeto.etapas.ProjetoEtapa1DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa2DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa3DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa4DTO;
import portal.editais.dto.projeto.etapas.ProjetoEtapa5DTO;
import portal.editais.dto.projeto.etapas.ProjetoResponseEtapa1DTO;
import portal.editais.dto.projeto.etapas.ProjetoResponseEtapa2DTO;
import portal.editais.dto.projeto.etapas.ProjetoResponseEtapa3DTO;
import portal.editais.dto.projeto.etapas.ProjetoResponseEtapa4DTO;
import portal.editais.dto.projeto.etapas.ProjetoResponseEtapa5DTO;
import portal.editais.entity.Projeto;
import portal.editais.service.projeto.ProjetoService;

@RestController
@RequestMapping("/projetos")
@Tag(name = "Projetos")
public class ProjetoController {

    private ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-1")
    @Operation(description = "Inserindo etapa 1 da submissão de projeto")
    public ResponseEntity<ProjetoResponseEtapa1DTO> createIdentificacaoProponente(
            @Valid @RequestBody ProjetoEtapa1DTO dto) throws Exception {

        Projeto entity = service.implementaProjetoEtapa1(dto);
        return ResponseEntity.ok(ProjetoResponseEtapa1DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-2/{id}")
    @Operation(description = "Inserindo etapa 2 da submissão de projeto")
    public ResponseEntity<ProjetoResponseEtapa2DTO> createCaracterizarProjeto(
            @Valid @RequestBody ProjetoEtapa2DTO dto, @PathVariable Integer id) throws Exception {

        Projeto entity = service.implementaProjetoEtapa2(id, dto);
        return ResponseEntity.ok(ProjetoResponseEtapa2DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-3/{id}")
    @Operation(description = "Inserindo etapa 3 da submissão de projeto")
    public ResponseEntity<ProjetoResponseEtapa3DTO> createLocalizacao(
            @Valid @RequestBody ProjetoEtapa3DTO dto, @PathVariable Integer id) throws Exception {

        Projeto entity = service.implementaProjetoEtapa3(id, dto);
        return ResponseEntity.ok(ProjetoResponseEtapa3DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-4/{id}")
    @Operation(description = "Inserindo etapa 4 da submissão de projeto")
    public ResponseEntity<ProjetoResponseEtapa4DTO> createPublicoBeneficiado(
            @Valid @RequestBody ProjetoEtapa4DTO dto, @PathVariable Integer id) throws Exception {

        Projeto entity = service.implementaProjetoEtapa4(id, dto);
        return ResponseEntity.ok(ProjetoResponseEtapa4DTO.toResponse(entity));
    }

    @Secured({ "ROLE_PROPONENTE" })
    @PostMapping("/etapa-5/{id}")
    @Operation(description = "Inserindo etapa 5 da submissão de projeto")
    public ResponseEntity<ProjetoResponseEtapa5DTO> createPublicoBeneficiado(
            @Valid @RequestBody ProjetoEtapa5DTO dto, @PathVariable Integer id) throws Exception {

        Projeto entity = service.implementaProjetoEtapa5(id, dto);
        return ResponseEntity.ok(ProjetoResponseEtapa5DTO.toResponse(entity));
    }

}
