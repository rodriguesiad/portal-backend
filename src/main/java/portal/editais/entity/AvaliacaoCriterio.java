package portal.editais.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "avaliacoes_criterios",
    uniqueConstraints = @UniqueConstraint(columnNames = {"subprojeto_id", "criterio_id", "avaliador_id"})
)
public class AvaliacaoCriterio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subprojeto_id", nullable = false)
    private Subprojeto subprojeto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criterio_id", nullable = false)
    private CriterioAvaliacao criterio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "avaliador_id", nullable = false)
    private User avaliador;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal nota;

    @Column(length = 1000)
    private String comentario;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    public void aoCriar() {
        LocalDateTime agora = LocalDateTime.now();
        criadoEm = agora;
        atualizadoEm = agora;
    }

    @PreUpdate
    public void aoAtualizar() {
        atualizadoEm = LocalDateTime.now();
    }
}
