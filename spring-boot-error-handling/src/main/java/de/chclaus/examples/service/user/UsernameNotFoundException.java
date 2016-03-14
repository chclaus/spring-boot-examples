package de.chclaus.examples.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Username not found.")
public class UsernameNotFoundException extends RuntimeException {

  public UsernameNotFoundException(String message) {
    super(message);
  }
}
