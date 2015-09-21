package fil.iagl.iir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao reservationDao;

	@Override
	public void sauvegarder(Reservation reservation) {
		if (reservation == null) {
			throw new RuntimeException("Parametre null");
		}
		this.reservationDao.sauvegarder(reservation);
	}

}
