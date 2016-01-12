package fil.iagl.iir.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.AdresseService;
import fil.iagl.iir.service.OffreService;
import fil.iagl.iir.service.UtilisateurService;
import fil.iagl.iir.service.VoteService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

  @Autowired
  private UtilisateurDao utilisateurDao;

  @Autowired
  private ParticulierDao particulierDao;

  @Autowired
  private AdresseService adresseService;

  @Autowired
  private OffreService offreService;

  @Autowired
  private VoteService voteService;

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.UtilisateurService#getById(java.lang.Integer)
   */
  @Override
  public Utilisateur getById(Integer id) {
    if (id == null) {
      throw new FeedMeException("Parametre null");
    }
    Utilisateur utilisateur = utilisateurDao.getById(id);
    utilisateur.setNote(getNoteUtilisateur(id));
    return utilisateur;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.UtilisateurService#getParticulierByUtilisisateurId(java.lang.Integer)
   */
  @Override
  public Particulier getParticulierByUtilisisateurId(Integer id) {
    if (id == null) {
      throw new FeedMeException("parametre null");
    }
    return particulierDao.getParticulierByUtilisateurId(id);
  }

  @Override
  public void modifierProfil(Particulier particulier) {
    if (particulier == null) {
      throw new FeedMeException("Parametre Particulier nul");
    }
    if (particulier.getAdresse().getId() == null) {
      adresseService.sauvegarder(particulier.getAdresse());
    }
    particulierDao.modifier(particulier);
  }

  @Override
  public List<Particulier> getAllPremium() {
    return this.particulierDao.getAllPremium();
  }

  public void devenirPrenium(Utilisateur utilisateur) {
    if (utilisateur == null) {
      throw new FeedMeException("Utilisateur null");
    }
    if (utilisateur.getPremium()) {
      throw new FeedMeException("Utilisateur déjà prénium");
    }
    utilisateurDao.devenirPrenium(utilisateur);
  }

  /*
   * Recherche toutes les offres pour lesquelles l'utilisateur est l'hôte.
   * Recupère la liste des votes pour chaque offre récupèrée.
   * Calcule la moyenne de toutes ces notes.
   */
  private Integer getNoteUtilisateur(Integer idUtilisateur) {
    // On collecte toutes les offres de l'hôte
    List<Offre> offres = this.offreService.getAllOffresByHote(idUtilisateur);

    // On récupère tous les votes
    List<Vote> votes = new ArrayList<Vote>();
    offres.stream().forEach(o -> votes.addAll(this.voteService.getVotesByOffre(o.getId())));

    return this.voteService.getNoteMoyenne(votes);
  }

}
