package portal.editais.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.CriterioAvaliacao;

public interface CriterioAvaliacaoRepository extends JpaRepository<CriterioAvaliacao, Integer> {
    List<CriterioAvaliacao> findByEditalIdOrderByOrdemAsc(Integer editalId);
}
