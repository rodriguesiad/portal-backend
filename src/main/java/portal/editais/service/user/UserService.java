package portal.editais.service.user;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.user.UserDTO;
import portal.editais.entity.User;
import portal.editais.enumeration.Profile;

import java.util.List;

public interface UserService {
    User create(UserDTO dto) throws ApiException;

    User findById(Integer id) throws ApiException;

    User getLoggedInUser() throws ApiException;

    List<User> listarUsuariosPorPerfil(Profile profile);
}
