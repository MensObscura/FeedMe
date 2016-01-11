package fil.iagl.iir.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.constante.CONSTANTE;
import fil.iagl.iir.dao.image.ImageDao;
import fil.iagl.iir.dao.offre.OffreDao;
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
  private ImageDao imageDao;

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
    if (!offre.getPremium() && offre.getImages().size() > CONSTANTE.NB_IMAGE_PAR_OFFRE_NON_PREMIUM) {
      throw new FeedMeException("Pour une offre non premium, une seule image seulement.");
    }

    offre.setHote(new Utilisateur(FeedMeSession.getIdUtilisateurConnecte()));

    this.adresseService.sauvegarder(offre.getAdresse());
    this.offreDao.sauvegarder(offre);
    offre.getImages().forEach(img -> this.imageDao.sauvegarderPourOffre(img.getId(), offre.getId()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#modifier(fil.iagl.iir.entite.Offre)
   */
  @Override
  public void modifier(Offre offre) {
    if (offre == null) {
      throw new FeedMeException("Parametre null");
    }

    if (offre.getNombrePersonne() == 0) {
      throw new FeedMeException("Nombre de convives pour l'offre ne doit pas être égal à 0");
    }

    if (!offre.getPremium() && offre.getImages().size() > CONSTANTE.NB_IMAGE_PAR_OFFRE_NON_PREMIUM) {
      throw new FeedMeException("Pour une offre non premium, une seule image seulement.");
    }

    if (LocalDateTime.now().isAfter(this.offreDao.getById(offre.getId()).getDateRepas().minusHours(CONSTANTE.NB_HEURE_POUR_CHANGER_OFFRE))) {
      throw new FeedMeException("On ne peut pas modifier une offre si celle ci commence dans moins de " + CONSTANTE.NB_HEURE_POUR_CHANGER_OFFRE + " heures.");
    }

    this.adresseService.sauvegarder(offre.getAdresse());
    this.imageDao.supprimerPourOffre(offre.getId());
    offre.getImages().forEach(img -> this.imageDao.sauvegarderPourOffre(img.getId(), offre.getId()));
    this.offreDao.modifier(offre);

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

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#listerOffresPremium()
   */
  @Override
  public List<Offre> listerOffresPremium() {
    return offreDao.getOffresPremium();
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#listerOffresParticipeUserConnecte()
   */
  @Override
  public List<Offre> listerOffresParticipeUserConnecte() {
    return offreDao.getOffresParticipeUserConnecte(FeedMeSession.getIdUtilisateurConnecte());
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#listerOffresCreesUserConnecte()
   */
  @Override
  public List<Offre> listerOffresCreesUserConnecte() {
    return offreDao.getAllOffresByHote(FeedMeSession.getIdUtilisateurConnecte());
  }

  @Override
  public List<Offre> listerOffresEnCoursByHote(Integer idUtilisateur) {
    return offreDao.getOffresEnCoursByHote(idUtilisateur);
  }

  @Override
  public List<Offre> getAllOffresByHote(Integer idUtilisateur) {
    return offreDao.getAllOffresByHote(idUtilisateur);
  }
}
