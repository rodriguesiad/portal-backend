package portal.editais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Projeto;
import portal.editais.enumeration.StatusProjeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>, JpaSpecificationExecutor<Projeto> {
        List<Projeto> findByEditalId(Integer editalId);

        List<Projeto> findByAutorId(Integer autorId);

        List<Projeto> findByAuditorId(Integer auditorId);

        Long countByStatus(StatusProjeto status);

        Long countByAutorId(Integer autorId);

        Long countByAutorIdAndStatus(Integer autorId, StatusProjeto status);
}
