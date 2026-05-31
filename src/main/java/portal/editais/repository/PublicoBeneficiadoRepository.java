package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import portal.editais.entity.PublicoBeneficiado;

@Repository
public interface PublicoBeneficiadoRepository
                extends JpaRepository<PublicoBeneficiado, Integer>, JpaSpecificationExecutor<PublicoBeneficiado> {
}
