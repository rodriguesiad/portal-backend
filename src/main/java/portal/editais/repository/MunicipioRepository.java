package portal.editais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.editais.entity.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
}
