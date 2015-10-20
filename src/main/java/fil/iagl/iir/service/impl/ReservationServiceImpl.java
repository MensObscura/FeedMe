package fil.iagl.iir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

  @Autowired
  private ReservationDao reservationDao;

  @Override
  public Integer sauvegarder(Reservation reservation) {
    if (reservation == null) {
      throw new FeedMeException("Parametre null");
    }
    reservation.setConvive(new Utilisateur(FeedMeSession.getIdUtilisateurConnecte()));

    return this.reservationDao.sauvegarder(reservation);

  }

  @Override
  public List<Reservation> getAllReservationByOffre(Integer offreId) {
    if (offreId == null) {
      throw new FeedMeException("Parametre null");
    }
    return reservationDao.getAllByIdOffre(offreId);
  }

}
