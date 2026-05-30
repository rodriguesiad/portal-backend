package portal.editais.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "plano_execucao")
public class PlanoExecucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 2000)
    private String objetivoGeral;

    @Column(nullable = false, length = 3000)
    private String objetivoEspecifico;

    @OneToMany(mappedBy = "planoExecucao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;

    @OneToOne(mappedBy = "planoExecucao")
    private Subprojeto subprojeto;

}
