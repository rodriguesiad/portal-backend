package portal.editais.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.DocumentoEdital;

public interface DocumentoEditalRepository extends JpaRepository<DocumentoEdital, Integer> {
    List<DocumentoEdital> findByEditalId(Integer editalId);

    boolean existsByEditalId(Integer editalId);
}
