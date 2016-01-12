package fil.iagl.iir.dao.vote;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Vote;

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

}
