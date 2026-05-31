package portal.editais.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.AvaliacaoCriterio;

public interface AvaliacaoCriterioRepository extends JpaRepository<AvaliacaoCriterio, Integer> {
    List<AvaliacaoCriterio> findBySubprojetoId(Integer subprojetoId);

    List<AvaliacaoCriterio> findBySubprojetoIdAndAvaliadorId(Integer subprojetoId, Integer avaliadorId);

    Optional<AvaliacaoCriterio> findBySubprojetoIdAndCriterioIdAndAvaliadorId(
        Integer subprojetoId,
        Integer criterioId,
        Integer avaliadorId
    );
}
