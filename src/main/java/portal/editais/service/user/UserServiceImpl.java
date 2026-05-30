package portal.editais.service.user;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.user.UserDTO;
import portal.editais.dto.user.UserUpdateDTO;
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
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setProfile(Profile.USER);

        String password = passwordEncoder.encode(dto.password());
        user.setPassword(password);

        return repository.save(user);
    }

    @Override
    @Transactional
    public User update(Integer id, UserUpdateDTO dto) throws ApiException {
        this.validateUser(id);

        User entity = repository.findById(id).orElseThrow(() -> new ApiException("Usuário não encontrado: " + id));
        entity.setName(dto.name());

        return repository.save(entity);
    }

    @Override
    public User findById(Integer id) throws ApiException {
        return repository.findById(id).orElseThrow(() -> new ApiException("Usuário não encontrado: " + id));
    }

    @Override
    public User getLoggedInUser() throws ApiException {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void validateUser(Integer id) throws ApiException {
        User loggedInUser = getLoggedInUser();
        if (!loggedInUser.getId().equals(id)) {
            throw new ApiException("Ação restrita ao dono do registro.");
        }
    }
}
