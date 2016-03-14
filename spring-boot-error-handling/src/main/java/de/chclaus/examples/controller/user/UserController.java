package de.chclaus.examples.controller.user;

import de.chclaus.examples.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/user/{username}")
  public UserDTO findUser(@PathVariable("username") String username) {
    return userService.findUserByName(username);
  }
}
