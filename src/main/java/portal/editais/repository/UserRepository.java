package portal.editais.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import portal.editais.entity.User;
import portal.editais.enumeration.Profile;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);

    List<User> findByProfile(Profile profile);
}
