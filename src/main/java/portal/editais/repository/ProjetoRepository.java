package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Projeto;
import portal.editais.enumeration.SituacaoProjeto;

@Repository
public interface ProjetoRepository
                extends JpaRepository<Projeto, Integer>, JpaSpecificationExecutor<Projeto> {
        Long countBySituacao(SituacaoProjeto situacao);

        Long countByAutorId(Integer autorId);

        Long countByAutorIdAndSituacao(Integer autorId, SituacaoProjeto situacao);
}
