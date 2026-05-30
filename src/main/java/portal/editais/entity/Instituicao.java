package portal.editais.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Data
@Entity
@Table(name = "instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(length = 200)
    private String nomeFantasia;

    @Column(nullable = false, length = 200)
    private String razaoSocial;

    @Column(nullable = false, length = 18)
    private String cnpj;

    @Column()
    private LocalDate dataFundacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_natureza_juridica")
    private NaturezaJuridica naturezaJuridica;

    @Column(length = 500)
    private String areaAtuacao;

    @Column(length = 255)
    private String site;

    @Column(length = 500)
    private String redesSociais;

    @OneToOne(mappedBy = "instituicao",
          cascade = CascadeType.ALL,
          orphanRemoval = true)
    private RepresentanteLegal representanteLegal;

    @Column(length = 500)
    private String situacao;

    @OneToOne(mappedBy = "instituicao")
    private Subprojeto subprojeto;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
