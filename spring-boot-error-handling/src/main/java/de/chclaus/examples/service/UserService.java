package de.chclaus.examples.service;

import de.chclaus.examples.controller.UserDTO;
import org.springframework.stereotype.Service;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@Service
public class UserService {

  public UserDTO findUserByName(String username) {
    return new UserDTO(username);
  }
}
