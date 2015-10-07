package fil.iagl.iir.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fil.iagl.iir.entite.Reservation;

@Service
public interface ReservationService {

	Integer sauvegarder(Reservation reservation);
	List<Reservation> getAllReservationByOffre(Integer offreId);

}
