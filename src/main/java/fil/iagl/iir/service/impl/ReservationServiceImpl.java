package fil.iagl.iir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.message.MessageDao;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.entite.Message;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

  private static final Integer BOT_ID = -1;

  @Autowired
  private ReservationDao reservationDao;

  @Autowired
  private MessageDao messageDao;

  @Autowired
  private ParticulierDao particulierDao;

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.ReservationService#sauvegarder(fil.iagl.iir.entite.Reservation)
   */
  @Override
  public Integer sauvegarder(Reservation reservation) {
    if (reservation == null) {
      throw new FeedMeException("Parametre null");
    }
    reservation.setConvive(new Utilisateur(FeedMeSession.getIdUtilisateurConnecte()));

    Message message = createAutoMessage(reservation);
    this.messageDao.sauvegarder(message);
    return this.reservationDao.sauvegarder(reservation);

  }

  private Message createAutoMessage(Reservation reservation) {
    Particulier utilisateurConnecte = particulierDao.getParticulierByUtilisateurId(FeedMeSession.getIdUtilisateurConnecte());
    Utilisateur bot = new Utilisateur(BOT_ID);
    String objet = String.format("Un convive à reservé pour \"%s\"", reservation.getOffre().getTitre());
    String texte = String
      .format("Bonjour,\n\n "
        + "Nous avons le plaisir de vous annoncer que %s %s vient de reserver %d place(s) pour votre annonce \"%s\".\n\n"
        + "Ceci est un message généré, merci ne pas repondre.", utilisateurConnecte.getPrenom(), utilisateurConnecte.getNom(), reservation.getNbPlaces(),
        reservation.getOffre().getTitre());

    Message message = new Message();
    message.setDestinataire(reservation.getOffre().getHote());
    message.setExpediteur(bot);
    message.setObjet(objet);
    message.setTexte(texte);
    return message;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.ReservationService#getAllReservationByOffre(java.lang.Integer)
   */
  @Override
  public List<Reservation> getAllReservationByOffre(Integer offreId) {
    if (offreId == null) {
      throw new FeedMeException("Parametre null");
    }
    return reservationDao.getAllByIdOffre(offreId);
  }

}
