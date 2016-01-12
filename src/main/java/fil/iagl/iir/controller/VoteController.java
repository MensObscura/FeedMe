package fil.iagl.iir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.DataReturn;
import fil.iagl.iir.outils.MessageSucces;
import fil.iagl.iir.service.VoteService;

@RestController
@RequestMapping("/vote")
public class VoteController {

  @Autowired
  private VoteService voteService;

  /**
   * Sauvegarde le vote pour une offre et un utilisateur donnés
   * 
   * @param vote le vote à sauvegarder
   * @return vote avec son id tel qu'il est enregistré dans la base
   */
  @RequestMapping(method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
  @MessageSucces("La sauvegarde s'est bien effectuée")
  public DataReturn<Vote> sauvegarder(@RequestBody Vote vote) {
    voteService.sauvegarder(vote);
    return new DataReturn<>(vote);
  }
}
