package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.NaturezaJuridica;

@Repository
public interface NaturezaJuridicaRepository
        extends JpaRepository<NaturezaJuridica, Integer>, JpaSpecificationExecutor<NaturezaJuridica> {
}
