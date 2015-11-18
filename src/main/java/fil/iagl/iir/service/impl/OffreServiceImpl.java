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
import fil.iagl.iir.service.AdresseService;
import fil.iagl.iir.service.OffreService;

@Service
public class OffreServiceImpl implements OffreService {

  @Autowired
  private OffreDao offreDao;

  @Autowired
  private AdresseDao adresseDao;

  @Autowired
  private VilleDao villeDao;

  @Autowired
  private AdresseService adresseService;

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#sauvegarder(fil.iagl.iir.entite.Offre)
   */
  @Override
  public void sauvegarder(Offre offre) {
    if (offre == null) {
      throw new FeedMeException("Parametre null");
    }
    if (offre.getNombrePersonne() == 0) {
      throw new FeedMeException("Nombre de convives pour l'offre ne doit pas être égal à 0");
    }

    offre.setHote(new Utilisateur(FeedMeSession.getIdUtilisateurConnecte()));

    this.adresseService.sauvegarder(offre.getAdresse());
    this.offreDao.sauvegarder(offre);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#afficher(java.lang.Integer)
   */
  @Override
  public Offre afficher(Integer id) {
    if (id == null) {
      throw new FeedMeException("Parametre null");
    }

    return this.offreDao.getById(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#lister()
   */
  @Override
  public List<Offre> lister() {
    return offreDao.getAll();
  }

}
