package fil.iagl.iir.controller;

import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.DataReturn;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.outils.MessageSucces;
import fil.iagl.iir.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  /**
   * Vérifie si l'utilisateur connecté a déjà voté pour une offre.
   * @param id l'ID de l'offre pour laquelle on vérifie le vote
   * @return <code>true</code> sit l'utilisateur connecté a déjà voté pour cette offre, <code>false</code> sinon. 
   */
  @RequestMapping(value = "/aDejaVote/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public DataReturn<Boolean> aDejaVote(@PathVariable("id") Integer id) {
    Vote vote = voteService.getVote(FeedMeSession.getIdUtilisateurConnecte(), id);
    return new DataReturn<>(vote != null);
  }
}
