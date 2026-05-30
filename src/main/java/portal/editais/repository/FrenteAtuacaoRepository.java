package portal.editais.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.FrenteAtuacao;

public interface FrenteAtuacaoRepository extends JpaRepository<FrenteAtuacao, Long> {
    Optional<FrenteAtuacao> findByIdAndAtivoTrue(Long id);

    boolean existsByCodigo(String codigo);

    List<FrenteAtuacao> findByAtivoTrueOrderByNomeAsc();
}
