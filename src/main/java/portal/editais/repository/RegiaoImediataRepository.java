package portal.editais.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.RegiaoImediata;

public interface RegiaoImediataRepository extends JpaRepository<RegiaoImediata, Long> {
    Optional<RegiaoImediata> findByIdAndAtivoTrue(Long id);

    boolean existsByCodigo(String codigo);

    List<RegiaoImediata> findByAtivoTrueOrderByNomeAsc();
}
