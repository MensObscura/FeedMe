package fil.iagl.iir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.entite.AuthentificationParticulier;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.service.AuthentificationService;
import fil.iagl.iir.service.UtilisateurService;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

  @Autowired
  private UtilisateurService utilisateurService;

  @Autowired
  private AuthentificationService authentificationService;

  /**
   * Retourne l'utilisateur correspondant à l'id donné
   * 
   * @param id
   *            d'un utilisateur
   * @return l'utilisateur correspondant à l'id
   */
  @RequestMapping(value = "/particulier/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public Particulier afficherProfil(@PathVariable("id") Integer id) {
    return utilisateurService.getParticulierByUtilisisateurId(id);
  }

  /**
   * 
   * @param auth
   *            Authenfication désignant un particulier qui souhaite
   *            s'inscrire sur FeedMe
   * @return Le particulier tel qu'inscrit dans la base de données
   */
  @RequestMapping(value = "/particulier", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
  public Particulier inscription(@RequestBody AuthentificationParticulier auth) {
    authentificationService.inscription(auth);
    return auth.getUtilisateur();
  }

  /**
   * Retourne le profil de l'utilisateur connecté
   * 
   * @return Le profil de l'utilisateur connecté
   */
  @RequestMapping(value = "/particulier/profil", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public Particulier afficherSonProfil() {
    Integer idSession = FeedMeSession.getIdUtilisateurConnecte();
    return utilisateurService.getParticulierByUtilisisateurId(idSession);
  }

  @RequestMapping(value = "/particulier/profil", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
  public Particulier modifierSonProfil(@RequestBody AuthentificationParticulier authentificationParticulier) {
    utilisateurService.modifierProfil(authentificationParticulier.getUtilisateur());
    return authentificationParticulier.getUtilisateur();
  }

  @RequestMapping(value = "/particulier/premium", method = RequestMethod.GET)
  public List<Particulier> getAllPremium() {
    return this.utilisateurService.getAllPremium();
  }

}
