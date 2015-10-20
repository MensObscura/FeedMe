package fil.iagl.iir.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;

public class ReservationServiceTest extends AbstractServiceTest {

  @Mock
  private Reservation reservation;

  @Mock
  private Utilisateur utilisateur;

  @Test
  public void sauvegarderTestSucces() throws Exception {
    Mockito.when(reservation.getConvive()).thenReturn(utilisateur);

    this.reservationService.sauvegarder(reservation);

    Mockito.verify(reservationDao, Mockito.times(1)).sauvegarder(reservation);
  }

  @Test(expected = RuntimeException.class)
  public void sauvegarderTestEchec() throws Exception {

    reservationService.sauvegarder(null);
    Mockito.verify(reservationDao, Mockito.never()).sauvegarder(Mockito.any());
  }

  @Test
  public void getAllReservationByOffreIdTestSuccess() throws Exception {
    Integer idOffre = 4;
    reservationService.getAllReservationByOffre(idOffre);
    Mockito.verify(reservationDao, Mockito.times(1)).getAllByIdOffre(idOffre);
  }

  @Test(expected = RuntimeException.class)
  public void getAllReservationByOffreIdFail() throws Exception {
    reservationService.getAllReservationByOffre(null);
    Mockito.verify(reservationDao, Mockito.never()).getAllByIdOffre(Mockito.any());
  }

}
