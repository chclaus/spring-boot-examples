package de.chclaus.examples.controller;

import de.chclaus.examples.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@RestController
public class ExampleController {

  @Autowired
  private UserService userService;

  @RequestMapping("/user/{username}")
  public UserDTO findUser(@PathVariable("username") String username) {
    return userService.findUserByName(username);
  }
}
