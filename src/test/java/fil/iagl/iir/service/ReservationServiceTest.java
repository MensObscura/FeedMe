package fil.iagl.iir.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Message;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;

public class ReservationServiceTest extends AbstractServiceTest {

  @Mock
  private Reservation reservation;

  @Mock
  private Particulier utilisateur;

  @Mock
  private Offre offre;

  @Test
  public void sauvegarderTestSucces() throws Exception {
    Mockito.when(reservation.getConvive()).thenReturn(utilisateur);
    Mockito.when(reservation.getOffre()).thenReturn(offre);
    Mockito.when(reservation.getNb_places()).thenReturn(4);
    Mockito.when(offre.getTitre()).thenReturn("MonTitre");
    Mockito.when(offre.getHote()).thenReturn(new Utilisateur());
    Mockito.when(particulierDao.getParticulierByUtilisateurId(FeedMeSession.getIdUtilisateurConnecte())).thenReturn(createParticulier());
    Mockito.when(utilisateur.getNom()).thenReturn("Nom");
    Mockito.when(utilisateur.getPrenom()).thenReturn("Prenom");

    this.reservationService.sauvegarder(reservation);

    Mockito.verify(reservationDao, Mockito.times(1)).sauvegarder(reservation);
    Mockito.verify(messageDao, Mockito.times(1)).sauvegarder(Mockito.any(Message.class));
  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec() throws Exception {

    reservationService.sauvegarder(null);
    Mockito.verify(reservationDao, Mockito.never()).sauvegarder(Mockito.any());
    Mockito.verify(messageDao, Mockito.never()).sauvegarder(Mockito.any());
  }

  @Test
  public void getAllReservationByOffreIdTestSuccess() throws Exception {
    Integer idOffre = 4;
    reservationService.getAllReservationByOffre(idOffre);
    Mockito.verify(reservationDao, Mockito.times(1)).getAllByIdOffre(idOffre);
  }

  @Test(expected = FeedMeException.class)
  public void getAllReservationByOffreIdFail() throws Exception {
    reservationService.getAllReservationByOffre(null);
    Mockito.verify(reservationDao, Mockito.never()).getAllByIdOffre(Mockito.any());
  }

}
