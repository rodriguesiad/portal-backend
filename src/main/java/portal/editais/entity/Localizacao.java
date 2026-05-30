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
@Table(name = "localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(length = 100)
    private String latitude;

    @Column(length = 100)
    private String longitude;

    @Column(nullable = false, length = 14)
    private String municipio; // TODO: Alterar para entidade município;

    @Column(length = 500)
    private String comunidade;

    @OneToOne(mappedBy = "localizacao")
    private Subprojeto subprojeto;

}
