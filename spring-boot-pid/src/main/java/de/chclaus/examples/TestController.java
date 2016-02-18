package de.chclaus.examples;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@Controller
public class TestController {

  @ResponseBody
  @RequestMapping("")
  public String index() {
    return "foo";
  }
}
