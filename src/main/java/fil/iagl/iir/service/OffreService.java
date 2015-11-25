package fil.iagl.iir.service;

import java.util.List;

import fil.iagl.iir.entite.Offre;

public interface OffreService {

  /**
   * Enregistre l'offre
   * @param offre L'offre a enregistrer
   *
   */
  void sauvegarder(Offre offre);

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
}
