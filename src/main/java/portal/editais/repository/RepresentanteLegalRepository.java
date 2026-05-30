package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.RepresentanteLegal;

@Repository
public interface RepresentanteLegalRepository
        extends JpaRepository<RepresentanteLegal, Integer>, JpaSpecificationExecutor<RepresentanteLegal> {
}
