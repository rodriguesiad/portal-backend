package portal.editais.dto.edital;

import java.math.BigDecimal;
import java.time.LocalDate;

import portal.editais.dto.user.UserResponseDTO;
import portal.editais.entity.Edital;
import portal.editais.entity.User;
import portal.editais.enumeration.StatusEdital;

public record EditalResumoResponseDTO(
    Integer id,
    String titulo,
    String estado,
    String orgaoProponente,
    String frenteAtuacao,
    String regiaoImediata,
    BigDecimal valorMinimo,
    BigDecimal valorMaximo,
    LocalDate inicioRecebimentoPropostas,
    LocalDate fimRecebimentoPropostas,
    StatusEdital status
) {
    public static EditalResumoResponseDTO toResponse(Edital entity) {
        return new EditalResumoResponseDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getEstado().getNome(),
                entity.getOrgaoProponente().getNome(),
                entity.getFrenteAtuacao().getNome(),
                entity.getRegiaoImediata().getNome(),
                entity.getValorMinimo(),
                entity.getValorMaximo(),
                entity.getInicioRecebimentoPropostas(),
                entity.getFimRecebimentoPropostas(),
                entity.getStatus());
    }
}
