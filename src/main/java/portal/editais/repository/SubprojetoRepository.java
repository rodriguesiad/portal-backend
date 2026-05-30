package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Subprojeto;

@Repository
public interface SubprojetoRepository
        extends JpaRepository<Subprojeto, Integer>, JpaSpecificationExecutor<Subprojeto> {
}
