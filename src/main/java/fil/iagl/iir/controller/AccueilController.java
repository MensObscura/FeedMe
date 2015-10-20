package fil.iagl.iir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccueilController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home() {
    return "redirect:/resources/index.html";
  }

}
