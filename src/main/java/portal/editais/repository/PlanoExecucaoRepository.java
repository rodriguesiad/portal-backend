package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.PlanoExecucao;

@Repository
public interface PlanoExecucaoRepository
                extends JpaRepository<PlanoExecucao, Integer>, JpaSpecificationExecutor<PlanoExecucao> {
}
