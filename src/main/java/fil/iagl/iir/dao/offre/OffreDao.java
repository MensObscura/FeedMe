package fil.iagl.iir.dao.offre;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Offre;

public interface OffreDao {

  /**
   * Recupere la liste de toutes les offres enregistrées.
   * 
   * @return Une liste d'offres
   */
  List<Offre> getAll();

  /**
   * Recupere une offre par son ID.
   * 
   * @param id
   *            l'ID de l'offre a recuperer
   * @return Une Offre
   */
  Offre getById(@Param("id") Integer id);

  /**
   * Enregistre une offre.
   * 
   * @param offre
   *            l'offre a sauvegarder
   * @return Le nombre de ligne inserees
   */
  Integer sauvegarder(@Param("offre") Offre offre);

  /**
   * Modifie une offre
   * 
   * @param offre l'offre à modifier
   * @return Le nombre de ligne modifié
   */
  Integer modifier(@Param("offre") Offre offre);

  /**
   * Récupère la liste des offres Premium
   * 
   * @return La liste des offres Premium
   */
  List<Offre> getOffresPremium();

  /**
   * Récupère la liste des offres auxquelles l'utilisateur connecté a participé
   * @param idUtilisateurConnecte id de l'utilisateur qui est connecté
   * @return La liste des offres auxquelles l'utilisateur connecté a participé
   */
  List<Offre> getOffresParticipeUserConnecte(@Param("idUtilisateurConnecte") Integer idUtilisateurConnecte);

  /**
   * Récupère la liste des offres que l'utilisateur connecté a créé
   * @param idUtilisateurConnecte id de l'utilisateur qui est connecté
   * @return La liste des offres créées par l'utilisateur connecté
   */
  List<Offre> getOffresCreesUserConnecte(@Param("idUtilisateurConnecte") Integer idUtilisateurConnecte);

  /**
   * Retourne la liste des offres en cours créées par un hôte donné
   * @param idUtilisateur id d'un hôte
   * @return La liste des offres en cours créées par l'hôte
   */
  List<Offre> getOffresEnCoursByHote(@Param("idUtilisateur") Integer idUtilisateur);
}
