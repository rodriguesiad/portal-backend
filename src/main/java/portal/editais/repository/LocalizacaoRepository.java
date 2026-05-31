package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Localizacao;

@Repository
public interface LocalizacaoRepository
        extends JpaRepository<Localizacao, Integer>, JpaSpecificationExecutor<Localizacao> {
}
