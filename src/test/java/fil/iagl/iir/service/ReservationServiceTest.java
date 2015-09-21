package fil.iagl.iir.service;

import org.junit.Test;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Reservation;

public class ReservationServiceTest extends AbstractServiceTest {

	@Test
	public void sauvegarderTestSucces() throws Exception {
		Reservation maReservation = this.createReservation();
		this.reservationService.sauvegarder(maReservation);
		Mockito.verify(reservationDao, Mockito.times(1)).sauvegarder(maReservation);
	}

	@Test(expected = RuntimeException.class)
	public void getByIdTestEchec() throws Exception {
		reservationService.sauvegarder(null);
		Mockito.verify(reservationDao, Mockito.times(0)).sauvegarder(null);
	}

}
