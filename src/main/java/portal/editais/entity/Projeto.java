package portal.editais.entity;

import java.time.LocalDateTime;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import portal.editais.enumeration.SituacaoProjeto;

@Data
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
    @JoinColumn(name = "id_edital")
    private Edital edital;

    @Column(length = 1500)
    private String resumo;

    @Column(length = 1500)
    private String justificativaMerito;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_publico_beneficiado")
    private PublicoBeneficiado publicoBeneficiado;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_plano_execucao")
    private PlanoExecucao planoExecucao;

    private Boolean declarouVeracidadeInformacoes;

    private Boolean autorizouTratamentoDadosLgpd;

    private Boolean comprometeuPrestacaoContas;

    private Boolean autorizouMonitoramentoAuditoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoProjeto situacao;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
