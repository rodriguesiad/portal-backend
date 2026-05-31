package portal.editais.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.AvaliacaoCriterio;

public interface AvaliacaoCriterioRepository extends JpaRepository<AvaliacaoCriterio, Integer> {
    Optional<AvaliacaoCriterio> findByProjetoIdAndCriterioIdAndAvaliadorId(
        Integer projetoId,
        Integer criterioId,
        Integer avaliadorId
    );

    List<AvaliacaoCriterio> findByProjetoId(Integer projetoId);
}
