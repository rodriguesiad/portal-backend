package portal.editais.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.Evidencia;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Integer> {
    List<Evidencia> findByProjetoIdOrderByCriadoEmDesc(Integer projetoId);
}
