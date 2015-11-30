package fil.iagl.iir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fil.iagl.iir.service.AuthentificationService;

@Controller
public class AccueilController {

  @Autowired
  private AuthentificationService authentificationService;

  /**
   * Redirige l'utilisateur vers la page d'accueil
   * 
   * @return Redirection vers la page d'accueil
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home() {
    return "redirect:index.html";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout() {
    this.authentificationService.logout();
    return "redirect:index.html";
  }

}
