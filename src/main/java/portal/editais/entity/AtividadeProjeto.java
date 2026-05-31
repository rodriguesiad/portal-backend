package portal.editais.entity;

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
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import portal.editais.enumeration.StatusAtividadeProjeto;

@Getter
@Setter
@Entity
@Table(name = "atividades_projeto")
public class AtividadeProjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 200)
    private String responsavel;

    private LocalDate inicio;

    private LocalDate fim;

    @Column(length = 1000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusAtividadeProjeto status = StatusAtividadeProjeto.PLANEJADA;
}
