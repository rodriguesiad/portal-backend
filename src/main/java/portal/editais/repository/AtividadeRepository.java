package portal.editais.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Atividade;

@Repository
public interface AtividadeRepository
        extends JpaRepository<Atividade, Integer>, JpaSpecificationExecutor<Atividade> {
    List<Atividade> findByEditalId(Integer editalId);

    List<Atividade> findByAutorId(Integer autorId);
}
