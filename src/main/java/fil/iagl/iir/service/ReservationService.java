package fil.iagl.iir.service;

import java.util.List;

import fil.iagl.iir.entite.Reservation;


public interface ReservationService {

	void sauvegarder(Reservation reservation);
	List<Reservation> getAllReservationByOffre(Integer offreId);

}
