package de.chclaus.examples.service.user;

import de.chclaus.examples.controller.user.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Simple service to demonstrate exception handling.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@Service
public class UserService {

  /**
   * Method to demonstrate a fake user lookup.
   * @param username The username... There is just an admin account.
   * @return a DTO representation of an user.
   */
  public UserDTO findUserByName(String username) {

    // We just have an administrator account. Nothing else. ;)
    if ("admin".equals(username)) {
      return new UserDTO(username);
    }

    throw new UsernameNotFoundException(String.format("Username not found. username=%s", username));
  }
}
