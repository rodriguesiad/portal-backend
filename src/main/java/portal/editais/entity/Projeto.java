package portal.editais.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import portal.editais.enumeration.StatusSubprojeto;

@Data
@Getter
@Setter
@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private User autor;

    @OneToOne
    @JoinColumn(name = "id_instituicao", nullable = false)
    private Instituicao instituicao;

    @Column(length = 200)
    private String nomeProjeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edital_id")
    private Edital edital;

    @Column(length = 1500)
    private String resumo;

    @Column(length = 1500)
    private String justificativaMerito;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusSubprojeto status = StatusSubprojeto.RASCUNHO;

    @OneToMany(mappedBy = "subprojeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtividadeSubprojeto> atividades = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_publico_beneficiado")
    private PublicoBeneficiado publicoBeneficiado;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_plano_execucao")
    private PlanoExecucao planoExecucao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auditor_id", nullable = false)
    private User auditor;

    private Boolean declarouVeracidadeInformacoes;

    private Boolean autorizouTratamentoDadosLgpd;

    private Boolean comprometeuPrestacaoContas;

    private Boolean autorizouMonitoramentoAuditoria;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = StatusSubprojeto.RASCUNHO;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
