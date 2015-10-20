package fil.iagl.iir.dao.utilisateur;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Utilisateur;

public interface UtilisateurDao {

  /**
   * Recupere un utilisateur par ID.
   * 
   * @param id
   *            l'ID de l'utilisateur a recuperer
   * @return l'Utilisateur associe a cet ID
   */
  Utilisateur getById(@Param("id") Integer id);

  /**
   * Enregistre un utilisateur
   * 
   * @param utilisateur
   *            l'Utilisateur a enregister
   * @return Le nombre de lignes inserees
   */
  Integer sauvegarder(@Param("utilisateur") Utilisateur utilisateur);

}
