package portal.editais.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import portal.editais.entity.Edital;
import portal.editais.enumeration.StatusEdital;

public interface EditalRepository extends JpaRepository<Edital, Integer>, JpaSpecificationExecutor<Edital> {
    List<Edital> findByStatusAndInicioRecebimentoPropostasLessThanEqual(StatusEdital status, LocalDate data);

    List<Edital> findByStatusAndFimRecebimentoPropostasBefore(StatusEdital status, LocalDate data);
}
