package portal.editais.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "edital_documentos")
public class DocumentoEdital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "edital_id", nullable = false)
    private Edital edital;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "documento_id", nullable = false)
    private Documento documento;

    private LocalDateTime criadoEm;

    @PrePersist
    public void aoCriar() {
        criadoEm = LocalDateTime.now();
    }
}
