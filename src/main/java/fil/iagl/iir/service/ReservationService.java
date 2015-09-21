package fil.iagl.iir.service;

import org.springframework.stereotype.Service;

import fil.iagl.iir.entite.Reservation;

@Service
public interface ReservationService {

	void sauvegarder(Reservation reservation);

}
