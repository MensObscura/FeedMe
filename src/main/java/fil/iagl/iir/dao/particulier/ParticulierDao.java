package fil.iagl.iir.dao.particulier;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Particulier;

public interface ParticulierDao {

  /**
   * Recupere un particulier par son ID.
   * 
   * @param id
   *            l'ID du particulier a recuperer
   * @return Un Particulier
   */
  Particulier getById(@Param("id") Integer id);

  /**
   * Enregistre un particulier.
   * 
   * @param particulier
   *            le Particulier a sauvegarder
   * @return le nombre de lignes inserees
   */
  Integer sauvegarder(@Param("particulier") Particulier particulier);

  /**
   * Recupere un particulier par ID d'utilisateur.
   * 
   * @param id
   *            l'ID de l'utilisateur qui est associe au particulier
   * @return Le Particulier associe a l'ID d'utilisateur
   */
  Particulier getParticulierByUtilisateurId(@Param("id") Integer id);

  /**
   * Modifier les informations d'un particulier
   * @param particulier
   *          Le particulier à modifier
   * @return Le nombre de lignes modifiées
   */
  Integer modifier(@Param("particulier") Particulier particulier);

  /**
   * Recuperer la liste de tous les utilisateurs premium
   * 
   * @return la liste de tous les utilisateurs premium
   */
  List<Particulier> getAllPremium();

}
