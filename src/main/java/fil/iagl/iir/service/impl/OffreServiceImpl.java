package fil.iagl.iir.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.constante.CONSTANTES;
import fil.iagl.iir.dao.image.ImageDao;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.service.AdresseService;
import fil.iagl.iir.service.OffreService;
import fil.iagl.iir.service.VoteService;

@Service
public class OffreServiceImpl implements OffreService {

  @Autowired
  private OffreDao offreDao;

  @Autowired
  private ImageDao imageDao;

  @Autowired
  private AdresseService adresseService;

  @Autowired
  private VoteService voteService;

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
    if (!offre.getPremium() && offre.getImages().size() > CONSTANTES.NB_IMAGE_PAR_OFFRE_NON_PREMIUM) {
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

    if (!offre.getPremium() && offre.getImages().size() > CONSTANTES.NB_IMAGE_PAR_OFFRE_NON_PREMIUM) {
      throw new FeedMeException("Pour une offre non premium, une seule image seulement.");
    }

    if (LocalDateTime.now().isAfter(this.offreDao.getById(offre.getId()).getDateRepas().minusHours(CONSTANTES.NB_HEURE_POUR_CHANGER_OFFRE))) {
      throw new FeedMeException("On ne peut pas modifier une offre si celle ci commence dans moins de " + CONSTANTES.NB_HEURE_POUR_CHANGER_OFFRE + " heures.");
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
    Offre offre = this.offreDao.getById(id);
    mettreAJourNoteOffres(Arrays.asList(offre));
    return offre;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#lister()
   */
  @Override
  public List<Offre> lister() {
    return mettreAJourNoteOffres(offreDao.getAll());
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#listerOffresPremium()
   */
  @Override
  public List<Offre> listerOffresPremium() {
    return mettreAJourNoteOffres(offreDao.getOffresPremium());
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#listerOffresParticipeUserConnecte()
   */
  @Override
  public List<Offre> listerOffresParticipeUserConnecte() {
    List<Offre> offres = offreDao.getOffresParticipeUserConnecte(FeedMeSession.getIdUtilisateurConnecte());
    return mettreAJourNoteOffres(offres);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.OffreService#listerOffresCreesUserConnecte()
   */
  @Override
  public List<Offre> listerOffresCreesUserConnecte() {
    List<Offre> offres = offreDao.getAllOffresByHote(FeedMeSession.getIdUtilisateurConnecte());
    return mettreAJourNoteOffres(offres);
  }

  @Override
  public List<Offre> listerOffresEnCoursByHote(Integer idUtilisateur) {
    List<Offre> offres = offreDao.getOffresEnCoursByHote(idUtilisateur);
    return mettreAJourNoteOffres(offres);
  }

  @Override
  public List<Offre> getAllOffresByHote(Integer idUtilisateur) {
    List<Offre> offres = offreDao.getAllOffresByHote(idUtilisateur);
    return mettreAJourNoteOffres(offres);
  }

  /*
   * Met a jour la note pour chaque offre de la liste
   * @param offres La liste des offres à mettre à jour
   * @return La liste des offres mises à jour
   */
  private List<Offre> mettreAJourNoteOffres(List<Offre> offres) {
    offres.stream().forEach(o -> {
      o.setNoteMoyenne(this.voteService.getNoteMoyenne(this.voteService.getVotesByOffre(o.getId())));
    });
    return offres;
  }
}
