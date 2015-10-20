package fil.iagl.iir.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fil.iagl.iir.entite.Reservation;

@Service
public interface ReservationService {

	/**
	 * Permet d'enregistrer une réservation
	 * @param reservation La réservation a enregistrer
	 * @return Le nombre de lignes insérées
	 */
  Integer sauvegarder(Reservation reservation);

  
  /**
   * Permet de récupérer toutes les réservations en rapport à une certaine offre
   * @param offreId L'id de l'offre dont on veut récupérer les réservations
   * @return Une liste de réservation
   */
  List<Reservation> getAllReservationByOffre(Integer offreId);

}
