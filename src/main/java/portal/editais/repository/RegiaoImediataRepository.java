package portal.editais.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.RegiaoImediata;

public interface RegiaoImediataRepository extends JpaRepository<RegiaoImediata, Integer> {
    Optional<RegiaoImediata> findByIdAndAtivoTrue(Integer id);

    boolean existsByCodigo(String codigo);

    List<RegiaoImediata> findByAtivoTrueOrderByNomeAsc();
}
