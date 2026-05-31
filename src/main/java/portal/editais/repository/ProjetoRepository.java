package portal.editais.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>, JpaSpecificationExecutor<Projeto> {
    List<Projeto> findBySubprojetoAutorId(Integer autorId);

    List<Projeto> findByAuditorId(Integer auditorId);

    Optional<Projeto> findBySubprojetoId(Integer subprojetoId);
}
