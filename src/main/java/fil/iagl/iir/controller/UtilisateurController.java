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
import fil.iagl.iir.outils.DataReturn;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.outils.MessageSucces;
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
  public DataReturn<Particulier> afficherProfil(@PathVariable("id") Integer id) {
    return new DataReturn<>(utilisateurService.getParticulierByUtilisisateurId(id));
  }

  /**
   * 
   * @param auth
   *            Authenfication désignant un particulier qui souhaite
   *            s'inscrire sur FeedMe
   * @return Le particulier tel qu'inscrit dans la base de données
   */
  @RequestMapping(value = "/particulier", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
  @MessageSucces("L'inscription a bien été prise en compte")
  public DataReturn<Particulier> inscription(@RequestBody AuthentificationParticulier auth) {
    authentificationService.inscription(auth);
    return new DataReturn<>(auth.getUtilisateur());
  }

  /**
   * Retourne le profil de l'utilisateur connecté
   * 
   * @return Le profil de l'utilisateur connecté
   */
  @RequestMapping(value = "/particulier/profil", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public DataReturn<Particulier> afficherSonProfil() {
    Integer idSession = FeedMeSession.getIdUtilisateurConnecte();
    return new DataReturn<>(utilisateurService.getParticulierByUtilisisateurId(idSession));
  }

  @RequestMapping(value = "/particulier/profil", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
  @MessageSucces("La modification du profil s'est bien effectuée")
  public DataReturn<Particulier> modifierSonProfil(@RequestBody Particulier particulier) {
    utilisateurService.modifierProfil(particulier);
    return new DataReturn<>(particulier);
  }

  @RequestMapping(value = "/particulier/premium", method = RequestMethod.GET)
  public DataReturn<List<Particulier>> getAllPremium() {
    return new DataReturn<>(this.utilisateurService.getAllPremium());
  }
  
  @RequestMapping(value="/particulier/devenirPrenium",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public DataReturn<Particulier> devenirPrenium(@RequestBody Particulier particulier) {
	 utilisateurService.devenirPrenium(particulier);
	 return new DataReturn<>(utilisateurService.getParticulierByUtilisisateurId(particulier.getIdParticulier()));
  }

}
