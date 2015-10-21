package fil.iagl.iir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.adresse.AdresseDao;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.service.OffreService;

@Service
public class OffreServiceImpl implements OffreService {

  @Autowired
  private OffreDao offreDao;

  @Autowired
  private AdresseDao adresseDao;

  @Autowired
  private VilleDao villeDao;

  /**
   * @see OffreService#sauvegarder(Offre)
   */
  @Override
  public void sauvegarder(Offre offre) {
    if (offre == null) {
      throw new FeedMeException("Parametre null");
    }
    if (offre.getNombrePersonne() == 0) {
      throw new FeedMeException("Nombre de convive pour l'offre ne doit pas être égal à 0");
    }

    offre.setHote(new Utilisateur(FeedMeSession.getIdUtilisateurConnecte()));

    // TODO : Creer un adresse service
    this.villeDao.sauvegarder(offre.getAdresse().getVille());
    this.adresseDao.sauvegarder(offre.getAdresse());

    this.offreDao.sauvegarder(offre);
  }

  /**
   * @see OffreService#afficher(Integer)
   */
  @Override
  public Offre afficher(Integer id) {
    if (id == null) {
      throw new FeedMeException("Parametre null");
    }

    return this.offreDao.getById(id);
  }

  /**
   * @see OffreService#lister()
   */
  @Override
  public List<Offre> lister() {
    return offreDao.getAll();
  }

}
