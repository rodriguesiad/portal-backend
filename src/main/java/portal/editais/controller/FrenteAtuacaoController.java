package portal.editais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portal.editais.dto.frenteatuacao.FrenteAtuacaoResponseDTO;
import portal.editais.service.frenteatuacao.FrenteAtuacaoService;

@RestController
@RequestMapping("/frentes-atuacao")
@Tag(name = "Frentes de Atuação")
public class FrenteAtuacaoController {

    private final FrenteAtuacaoService frenteAtuacaoService;

    public FrenteAtuacaoController(FrenteAtuacaoService frenteAtuacaoService) {
        this.frenteAtuacaoService = frenteAtuacaoService;
    }

    @GetMapping
    @Operation(summary = "Listar frentes de atuação ativas")
    public ResponseEntity<List<FrenteAtuacaoResponseDTO>> listarFrentesAtuacaoAtivas() {
        return ResponseEntity.ok(frenteAtuacaoService.listarFrentesAtuacaoAtivas());
    }
}
