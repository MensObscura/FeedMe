package fil.iagl.iir.service;

import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.FeedMeException;

public class VoteServiceTest extends AbstractServiceTest {

  @Test
  public void sauvegarderTestSucces() throws Exception {
    // Etant donné un vote
    Vote vote = this.createVote();

    // Quand on appelle le service de sauvegarde de vote
    voteService.sauvegarder(vote);

    // Alors on vérifie que la DAO a bien été appelée avec ce vote
    Mockito.verify(voteDao, Mockito.times(1)).sauvegarder(vote);
  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec() throws Exception {
    // Quand on appelle le service de sauvegarde d'un vote nul
    voteService.sauvegarder(null);

    // Alors on vérifie que la DAO n'est pas appelée
    Mockito.verify(voteDao, Mockito.never()).sauvegarder(Mockito.any());

    // Et qu'une FeedMeException est lancée
  }

  @Test
  public void getVotesByOffreTestSucces() throws Exception {
    // Etant donnée une liste de votes renvoyée par la DAO
    Integer idOffre = 1;
    List<Vote> mockVotes = buildListeVotes();

    Mockito.when(voteDao.getVotesByOffre(idOffre)).thenReturn(mockVotes);

    // Quand je récupère la liste des votes pour une offre donnée
    List<Vote> listeResultante = this.voteService.getVotesByOffre(idOffre);

    // Alors je veux récupèrer les bonnes données
    Assertions.assertThat(listeResultante).isEqualTo(mockVotes);

    // Et on vérifie que la DAO a bien été appelée
    Mockito.verify(voteDao, Mockito.times(1)).getVotesByOffre(idOffre);
  }

  @Test(expected = FeedMeException.class)
  public void getVotesByOffreTestEchec() throws Exception {
    // Quand on appelle le service pour une sauvegarde de vote nul
    voteService.getVotesByOffre(null);

    // Alors on vérifie que la DAO n'est pas appelée
    Mockito.verify(voteDao, Mockito.never()).getVotesByOffre(Mockito.any());

    // Et qu'une FeedMeException est lancée
  }

  /* ******************************* BUILD *******************************/

  private List<Vote> buildListeVotes() {
    List<Vote> votes = new ArrayList<Vote>();
    Vote vote1 = this.createVote();
    Vote vote2 = this.createVote();
    vote2.getUtilisateur().setIdUtilisateur(2);
    votes.add(vote1);
    votes.add(vote2);
    return votes;
  }

}
