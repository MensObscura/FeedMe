package fil.iagl.iir.dao;

import static fil.iagl.iir.predicat.Predicats.estNoteValide;

import java.util.List;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.SQLCODE;

public class VoteDaoTest extends AbstractDaoTest {

  @Test
  public void getVotesByOffreTestSucces() throws Exception {
    // Etant donné qu'une offre a reçu 3 notes par différents utilisateurs
    Integer idOffre = 1;
    Integer nombreVotesAttendu = 3;

    // Quand on récupère la liste des votes pour cette offre
    List<Vote> votes = voteDao.getVotesByOffre(idOffre);

    // Alors on obtient une liste de votes non vide
    Assertions.assertThat(votes).isNotNull().isNotEmpty().hasSize(nombreVotesAttendu);
    // On vérifie que toutes les notes sont valides
    Assertions.assertThat(votes).are(new Condition<Vote>() {
      @Override
      public boolean matches(Vote vote) {
        return estNoteValide.test(vote.getNote());
      }
    });
  }

  @Test
  public void getVotesByOffreTestSucces_AucuneNote() throws Exception {
    // Etant donné qu'une offre n'a jamais été noté
    // Quand on récupère la liste des notes qui lui sont attribuées
    // Alors on obtient une liste de votes vide
    Assertions.assertThat(voteDao.getVotesByOffre(2)).isNotNull().isEmpty();
  }

  @Test
  public void getVotesByOffreTestSucces_IdOffreNul() throws Exception {
    // Quand on tente de récupèrer la liste des notes avec un ID d'offre nul
    // On obtient une liste de votes vide
    Assertions.assertThat(voteDao.getVotesByOffre(null)).isNotNull().isEmpty();
  }

  @Test
  public void getVotesByOffreTestEchec_IdOffreInconnu() throws Exception {
    // De même avec un ID d'offre inconnu
    Assertions.assertThat(voteDao.getVotesByOffre(99)).isNotNull().isEmpty();
  }

  @Test
  public void sauvegarderUnVoteTestSucces() throws Exception {
    // Etant donnée un vote de l'utilisateur sur une offre
    Vote vote = createVote();

    // Quand on sauvegarde le vote
    voteDao.sauvegarder(vote);

    // On vérifie que l'ID du vote est bien à jour
    Assertions.assertThat(vote.getId()).isNotNull().isPositive();
    Assertions.assertThat(vote.getUtilisateur()).isNotNull();
    Assertions.assertThat(vote.getOffre()).isNotNull();
    Assertions.assertThat(vote.getNote()).isNotNull().isPositive();
  }

  @Test
  public void sauvegarderUnVoteTestEchec_VoteNul() throws Exception {
    try {
      // Quand on enregistre un vote nul
      voteDao.sauvegarder(null);

      // Alors on attend à ce qu'une exception soit lancée
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderUnVoteTestEchec_VoteExistant() throws Exception {
    // Etant donnée un vote existant
    Vote vote = createVote();
    vote.getOffre().setId(1);

    try {
      // Quand on enregistre une seconde fois ce vote
      voteDao.sauvegarder(vote);

      // Alors on attend à ce qu'une exception soit lancée
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.UNIQUE_VIOLATION);
    }
  }

  @Test
  public void sauvegarderUnVoteTestEchec_NoteNonValide() throws Exception {
    // Etant donnée un vote existant
    Vote vote = createVote();
    vote.setNote(15);

    try {
      // Quand on enregistre une seconde fois ce vote
      voteDao.sauvegarder(vote);

      // Alors on attend à ce qu'une exception soit lancée
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.CHECK_VIOLATION);
    }
  }

  @Test
  public void sauvegarderUnVoteTestEchec_NoteNegative() throws Exception {
    // Etant donnée un vote existant
    Vote vote = createVote();
    vote.setNote(-5);

    try {
      // Quand on enregistre une seconde fois ce vote
      voteDao.sauvegarder(vote);

      // Alors on attend à ce qu'une exception soit lancée
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.CHECK_VIOLATION);
    }
  }

}
