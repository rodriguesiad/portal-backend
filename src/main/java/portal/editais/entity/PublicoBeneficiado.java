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
@Table(name = "publico_beneficiado")
public class PublicoBeneficiado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer mulheresQuant;

    @Column(nullable = false)
    private Integer homensQuant;

    @Column(nullable = false)
    private Integer criancasQuant;

    @Column(nullable = false)
    private Integer jovensQuant;

    @Column(nullable = false)
    private Integer idososQuant;

    @Column(nullable = false)
    private Integer povosIndigenasQuant;

    @Column(nullable = false)
    private Integer quilombolasQuant;

    @Column(nullable = false)
    private Integer agricultoresFamiliarQuant;

    @Column(nullable = false)
    private Integer comunidadesTradicionaisQuant;

    private Float rendaMedia;

    @Column(length = 100)
    private String fonteRendaPrincipal;

    @Column(nullable = false, length = 1500)
    private String descricaoAplicacaoBeneficio;

    @OneToOne(mappedBy = "publicoBeneficiado")
    private Subprojeto subprojeto;

}
