package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Projeto;

@Repository
public interface ProjetoRepository
        extends JpaRepository<Projeto, Integer>, JpaSpecificationExecutor<Projeto> {
}
