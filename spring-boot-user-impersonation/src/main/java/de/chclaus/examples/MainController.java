package de.chclaus.examples;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chclaus (ch.claus@me.com)
 */
@Controller
public class MainController {

  @RequestMapping("/user")
  public String userPage(Model model) {
    model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return "user";
  }

  @RequestMapping("/admin")
  public String adminPage() {
    return "admin";
  }
}
