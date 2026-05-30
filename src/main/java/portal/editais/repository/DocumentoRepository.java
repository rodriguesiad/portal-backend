package portal.editais.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import portal.editais.entity.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, UUID> {
}
