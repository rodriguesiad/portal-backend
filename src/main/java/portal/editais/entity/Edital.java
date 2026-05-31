package portal.editais.entity;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portal.editais.enumeration.StatusEdital;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "editais")
public class Edital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgao_proponente_id")
    private OrgaoProponente orgaoProponente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "frente_atuacao_id", nullable = false)
    private FrenteAtuacao frenteAtuacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regiao_imediata_id", nullable = false)
    private RegiaoImediata regiaoImediata;

    @ManyToMany
    @JoinTable(
        name = "edital_avaliadores",
        joinColumns = @JoinColumn(name = "edital_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    @Builder.Default
    private Set<User> avaliadores = new HashSet<>();

    @OneToMany(mappedBy = "edital", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CriterioAvaliacao> criterios = new ArrayList<>();

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorMinimo;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorMaximo;

    @Column(nullable = false)
    private LocalDate inicioRecebimentoPropostas;

    @Column(nullable = false)
    private LocalDate fimRecebimentoPropostas;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String resumo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusEdital status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    public void aoCriar() {
        LocalDateTime agora = LocalDateTime.now();
        criadoEm = agora;
        atualizadoEm = agora;
        if (status == null) {
            status = StatusEdital.RASCUNHO;
        }
    }

    @PreUpdate
    public void aoAtualizar() {
        atualizadoEm = LocalDateTime.now();
    }
}
