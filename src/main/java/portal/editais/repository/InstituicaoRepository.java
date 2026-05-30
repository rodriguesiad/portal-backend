package portal.editais.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Instituicao;

@Repository
public interface InstituicaoRepository
                extends JpaRepository<Instituicao, Integer>, JpaSpecificationExecutor<Instituicao> {
        Optional<Instituicao> findInstituicaoByCnpj(String cnpj);
}
