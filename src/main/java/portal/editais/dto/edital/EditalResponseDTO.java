package portal.editais.dto.edital;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import portal.editais.dto.documento.DocumentoVinculadoResponseDTO;
import portal.editais.entity.Edital;
import portal.editais.enumeration.StatusEdital;

public record EditalResponseDTO(
    Integer id,
    String titulo,
    Integer estadoId,
    String estado,
    Integer orgaoProponenteId,
    String orgaoProponente,
    Integer frenteAtuacaoId,
    String frenteAtuacao,
    Integer regiaoImediataId,
    String regiaoImediata,
    List<DocumentoVinculadoResponseDTO> documentos,
    BigDecimal valorMinimo,
    BigDecimal valorMaximo,
    LocalDate inicioRecebimentoPropostas,
    LocalDate fimRecebimentoPropostas,
    String resumo,
    StatusEdital status,
    LocalDateTime criadoEm,
    LocalDateTime atualizadoEm
) {

    public static EditalResponseDTO toResponse(Edital edital, List<DocumentoVinculadoResponseDTO> documentos) {
        return new EditalResponseDTO(
                edital.getId(),
                edital.getTitulo(),
                edital.getEstado().getId(),
                edital.getEstado().getNome(),
                edital.getOrgaoProponente().getId(),
                edital.getOrgaoProponente().getNome(),
                edital.getFrenteAtuacao().getId(),
                edital.getFrenteAtuacao().getNome(),
                edital.getRegiaoImediata().getId(),
                edital.getRegiaoImediata().getNome(),
                documentos,
                edital.getValorMinimo(),
                edital.getValorMaximo(),
                edital.getInicioRecebimentoPropostas(),
                edital.getFimRecebimentoPropostas(),
                edital.getResumo(),
                edital.getStatus(),
                edital.getCriadoEm(),
                edital.getAtualizadoEm()
        );
    }
}
