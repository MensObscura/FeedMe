package fil.iagl.iir.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeSession;

public class ReservationServiceTest extends AbstractServiceTest {

	@Mock
	private Reservation reservation;

	@Mock
	private Utilisateur utilisateur;

	@Test
	public void sauvegarderTestSucces() throws Exception {
		Mockito.when(reservation.getConvive()).thenReturn(utilisateur);

		this.reservationService.sauvegarder(reservation);

		Mockito.verify(utilisateur, Mockito.times(1)).setIdUtilisateur(FeedMeSession.getIdUtilisateurConnecte());
		Mockito.verify(reservationDao, Mockito.times(1)).sauvegarder(reservation);
	}

	@Test(expected = RuntimeException.class)
	public void sauvegarderTestEchec() throws Exception {
		reservationService.sauvegarder(null);
		Mockito.verify(reservationDao, Mockito.never()).sauvegarder(Mockito.any());
	}

}
