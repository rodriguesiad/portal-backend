package portal.editais.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findBySiglaAndAtivoTrue(String sigla);

    boolean existsBySigla(String sigla);
}
