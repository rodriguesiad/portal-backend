package portal.editais.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    List<Projeto> findBySubprojetoAutorId(Integer autorId);

    List<Projeto> findByAuditorId(Integer auditorId);

    Optional<Projeto> findBySubprojetoId(Integer subprojetoId);
}
