package fil.iagl.iir.dao.vote;

import fil.iagl.iir.entite.Vote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteDao {

  /**
   * Retourne la liste des votes pour une offre donnée.
   * @param idOffre l'offre qui est notée
   * @return La liste des votes des utilisateurs pour cette offre
   */
  public List<Vote> getVotesByOffre(@Param("idOffre") Integer idOffre);
  
  /**
   * Sauvegarde un vote en base.
   * @param vote le vote à sauvegarder
   * @return Le nombre de lignes insérées
   */
  public Integer sauvegarder(@Param("vote") Vote vote);

  /**
   * Retourne le vote d'un utilisateur pour une offre.
   * @param idutilisateur l'ID de l'utilisateur
   * @param idOffre l'ID de l'offre
   * @return le vote de l'utilisateur pour cette offre
   */
  public Vote getVote(@Param("idUtilisateur") Integer idutilisateur, @Param("idOffre") Integer idOffre);
}
