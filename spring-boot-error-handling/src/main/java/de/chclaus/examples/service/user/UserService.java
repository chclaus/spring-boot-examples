package de.chclaus.examples.service.user;

import de.chclaus.examples.controller.user.UserDTO;
import org.springframework.stereotype.Service;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@Service
public class UserService {

  public UserDTO findUserByName(String username) {

    // We just have an administrator account. Nothing else. ;)
    if ("admin".equals(username)) {
      return new UserDTO(username);
    }

    throw new UsernameNotFoundException(String.format("Username not found. username=%s", username));
  }
}
