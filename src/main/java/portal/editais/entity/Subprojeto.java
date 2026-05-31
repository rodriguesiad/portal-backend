package portal.editais.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import portal.editais.enumeration.StatusSubprojeto;

@Data
@Entity
@Table(name = "subprojeto")
public class Subprojeto {

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
    private String nomeSubprojeto;

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
