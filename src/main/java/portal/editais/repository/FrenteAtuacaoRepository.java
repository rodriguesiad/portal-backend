package portal.editais.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.FrenteAtuacao;

public interface FrenteAtuacaoRepository extends JpaRepository<FrenteAtuacao, Integer> {
    Optional<FrenteAtuacao> findByIdAndAtivoTrue(Integer id);

    boolean existsByCodigo(String codigo);

    List<FrenteAtuacao> findByAtivoTrueOrderByNomeAsc();
}
