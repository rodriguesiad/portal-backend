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
import portal.editais.dto.avaliacao.AvaliacaoDTO;
import portal.editais.dto.avaliacao.ProjetoAvaliacaoResponseDTO;
import portal.editais.dto.avaliacao.RankingPropostaResponseDTO;
import portal.editais.dto.avaliacao.SelecionarVencedorDTO;
import portal.editais.dto.edital.EditalResumoResponseDTO;
import portal.editais.dto.projeto.ProjetoResponseDTO;
import portal.editais.service.avaliacao.AvaliacaoService;

@RestController
@RequestMapping("/avaliador")
@Tag(name = "Avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @Secured("ROLE_AVALIADOR")
    @GetMapping("/editais")
    public ResponseEntity<List<EditalResumoResponseDTO>> listarEditais() {
        return ResponseEntity.ok(avaliacaoService.listarEditaisDoAvaliador());
    }

    @Secured("ROLE_AVALIADOR")
    @GetMapping("/editais/{id}/projetos")
    public ResponseEntity<List<ProjetoAvaliacaoResponseDTO>> listarProjetos(@PathVariable Integer id) {
        return ResponseEntity.ok(avaliacaoService.listarProjetos(id));
    }

    @Secured("ROLE_AVALIADOR")
    @PostMapping("/projetos/{id}/avaliacoes")
    public ResponseEntity<Void> avaliar(@PathVariable Integer id, @RequestBody @Valid AvaliacaoDTO dto) {
        avaliacaoService.salvarAvaliacao(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_AVALIADOR")
    @GetMapping("/editais/{id}/ranking")
    public ResponseEntity<List<RankingPropostaResponseDTO>> ranking(@PathVariable Integer id) {
        return ResponseEntity.ok(avaliacaoService.ranking(id));
    }

    @Secured("ROLE_AVALIADOR")
    @PostMapping("/editais/{id}/selecionar-vencedor")
    public ResponseEntity<ProjetoResponseDTO> selecionarVencedor(
            @PathVariable Integer id,
            @RequestBody @Valid SelecionarVencedorDTO dto) {
        return ResponseEntity.ok(avaliacaoService.selecionarVencedor(id, dto));
    }
}
