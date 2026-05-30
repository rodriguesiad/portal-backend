package portal.editais.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.OrgaoProponente;

public interface OrgaoProponenteRepository extends JpaRepository<OrgaoProponente, Long> {
    Optional<OrgaoProponente> findByIdAndAtivoTrue(Long id);

    List<OrgaoProponente> findByEstadoSiglaAndAtivoTrueOrderByNomeAsc(String sigla);

    boolean existsByCodigo(String codigo);
}
