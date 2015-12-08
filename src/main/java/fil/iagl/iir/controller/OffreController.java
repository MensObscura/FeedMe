package fil.iagl.iir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.entite.Offre;
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
  public Offre sauvegarder(@RequestBody Offre offre) {
    offreservice.sauvegarder(offre);
    return offre;
  }

  /**
   * Retourne l'offre correspondant à un id donné
   * 
   * @param id d'une offre
   * @return L'offre correspondant à l'id.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Offre afficher(@PathVariable("id") Integer id) {
    return offreservice.afficher(id);
  }

  /**
   * Retourne toutes les offres de la base de données
   * 
   * @return la liste de toutes les offres
   */
  @RequestMapping(method = RequestMethod.GET)
  public List<Offre> afficher() {
    return offreservice.lister();
  }

  /**
   * Retourne les offres Premium de la base de données
   * 
   * @return La liste des offres Premium
   */
  @RequestMapping(value = "/premium", method = RequestMethod.GET)
  public List<Offre> afficherOffresPremium() {
    return offreservice.listerOffresPremium();
  }

  @RequestMapping(value = "/aParticipe", method = RequestMethod.GET)
  public List<Offre> afficherOffresParticipeUserConnecte() {
    return offreservice.listerOffresParticipeUserConnecte();
  }

}
