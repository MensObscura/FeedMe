package fil.iagl.iir.service;

import java.util.List;

import fil.iagl.iir.entite.Filtres;
import fil.iagl.iir.entite.Offre;

public interface OffreService {

  /**
   * Enregistre l'offre
   * 
   * @param offre L'offre à enregistrer
   *
   */
  void sauvegarder(Offre offre);

  /**
   * Modifie une offre
   * 
   * @param offre L'offre à modifier
   */
  void modifier(Offre offre);

  /**
   * Permet de récupérer une offre selon son ID
   * @param id L'ID de l'offre a récupérer
   * @return L'offre sélectionnée
   */
  Offre afficher(Integer id);

  /**
   * Permet de récupérer la liste de toutes les offres
   * @return La liste de toutes les offres
   */
  List<Offre> lister();

  /**
   * Permet de récupérer la liste des offres Premium
   * @return La liste des offres Premium
   */
  List<Offre> listerOffresPremium();

  /**
   * Permet de récupérer la liste des offres auxquelles l'utilisateur connecté a participé
   * @return La liste des offres auxquelles l'utilisateur connecté a participé
   */
  List<Offre> listerOffresParticipeUserConnecte();

  /**
   * Permet de récupérer la liste des offres créées par l'utilisateur connecté
   * @return la liste des offres créées par l'utilisateur connecté
   */
  List<Offre> listerOffresCreesUserConnecte();

  /**
   * Permet de récupérer la liste des offres en cours d'un hôte donné
   * @param idUtilisateur L'id de l'hôte
   * @return La liste des offres en cours d'un hôte donné
   */
  List<Offre> listerOffresEnCoursByHote(Integer idUtilisateur);

  /**
   * Permet de récupèrer la liste de toutes les offres proposées par un hôte.
   * @param idUtilisateur l'hôte dont on recherche les offres
   * @return La liste de toutes les offres proposées par l'hôte
   */
  List<Offre> getAllOffresByHote(Integer idUtilisateur);

  /**
   * Permet de faire une recherche d'offre en fonction des critères choisis
   * 
   * @param filtre les filtres choisis
   * @return la liste d'offres correspondante
   */
  List<Offre> recherche(Filtres filtre);
}
