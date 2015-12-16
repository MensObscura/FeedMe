package fil.iagl.iir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.outils.DataReturn;
import fil.iagl.iir.outils.MessageSucces;
import fil.iagl.iir.service.OffreService;

@RestController
@RequestMapping("/offres")
public class OffreController {

  @Autowired
  private OffreService offreservice;

  /**
   * Sauvegarde l'offre dans la base de données
   * 
   * @param offre à sauvegarder dans la base
   * @return offre avec son id tel qu'elle est enregistrée dans la base
   */
  @RequestMapping(method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
  @MessageSucces("La sauvegarde s'est bien effectuée")
  public DataReturn<Offre> sauvegarder(@RequestBody Offre offre) {
    offreservice.sauvegarder(offre);
    return new DataReturn<>(offre);
  }

  /**
   * Modifier l'offre dans la base de données
   * 
   * @param offre à modifier dans la base
   * @return offre avec son id tel qu'elle est enregistrée dans la base
   */
  @RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @MessageSucces("La modification s'est bien effectuée")
  public DataReturn<Offre> modifier(@RequestBody Offre offre) {
    offreservice.modifier(offre);
    return new DataReturn<>(offre);
  }

  /**
   * Retourne l'offre correspondant à un id donné
   * 
   * @param id d'une offre
   * @return L'offre correspondant à l'id.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public DataReturn<Offre> afficher(@PathVariable("id") Integer id) {
    return new DataReturn<>(offreservice.afficher(id));
  }

  /**
   * Retourne toutes les offres de la base de données
   * 
   * @return la liste de toutes les offres
   */
  @RequestMapping(method = RequestMethod.GET)
  public DataReturn<List<Offre>> afficher() {
    return new DataReturn<>(offreservice.lister());
  }

  /**
   * Retourne les offres Premium de la base de données
   * 
   * @return La liste des offres Premium
   */
  @RequestMapping(value = "/premium", method = RequestMethod.GET)
  public DataReturn<List<Offre>> afficherOffresPremium() {
    return new DataReturn<>(offreservice.listerOffresPremium());
  }

  /**
   * Retourne les offres auxquelles l'utilisateur connecté a participé
   * @return La liste des offres auxquelles l'utilisateur connecté a participé
   */
  @RequestMapping(value = "/aParticipe", method = RequestMethod.GET)
  public DataReturn<List<Offre>> afficherOffresParticipeUserConnecte() {
    return new DataReturn<>(offreservice.listerOffresParticipeUserConnecte());
  }

  /**
   * Retourne la listes des offres créées par l'utilisateur connecté
   * @return La liste des offres créees par l'utilisateur connecté
   */
  @RequestMapping(value = "/aCree", method = RequestMethod.GET)
  public DataReturn<List<Offre>> afficherOffresCreesUserConnecte() {
    return new DataReturn<>(offreservice.listerOffresCreesUserConnecte());
  }

  @RequestMapping(value = "/enCours/{idHote}", method = RequestMethod.GET)
  public DataReturn<List<Offre>> afficherOffresEnCoursByHote(@PathVariable("idHote") Integer idHote) {
	return new DataReturn<>(offreservice.listerOffresEnCoursByHote(idHote));
  }
}
