package portal.editais.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "representante_legal")
public class RepresentanteLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String nomeCompleto;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(length = 100)
    private String rg;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 24)
    private String telefone;

    @Column(nullable = false, length = 200)
    private String cargo;

    @OneToOne(mappedBy = "representanteLegal")
    private Instituicao instituicao;

}
