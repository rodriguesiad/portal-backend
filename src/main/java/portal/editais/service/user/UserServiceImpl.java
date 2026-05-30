package portal.editais.service.user;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.user.UserDTO;
import portal.editais.entity.User;
import portal.editais.enumeration.Profile;
import portal.editais.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    protected UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User create(UserDTO dto) throws ApiException {
        Optional<User> cadastredUser = this.repository.findUserByEmail(dto.email());

        if (cadastredUser.isPresent()) {
            throw new ApiException("Usuário já cadastrado.");
        }

        User user = new User();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setProfile(Profile.PROPONENTE);

        String password = passwordEncoder.encode(dto.password());
        user.setPassword(password);

        return repository.save(user);
    }

    @Override
    public User findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Usuário não encontrado: " + id));
    }

    @Override
    public User getLoggedInUser() throws ApiException {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
