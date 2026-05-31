package portal.editais.dto.projeto;

import java.util.List;

public record ProjetoFilterDTO(
        String nomeProjeto,
        String tituloEdital,
        List<String> status) {
}
