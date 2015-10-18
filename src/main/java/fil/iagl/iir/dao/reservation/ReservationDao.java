package fil.iagl.iir.dao.reservation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Reservation;

public interface ReservationDao {

	/**
	 * Enregistre une reservation.
	 * 
	 * @param reservation
	 *            la reservation a sauvegarder
	 * @return Le nombre de lignes inserees
	 */
	Integer sauvegarder(@Param("reservation") Reservation reservation);

	/**
	 * Recupere toutes les reservations associees a une offre
	 * 
	 * @param id
	 *            l'ID de l'offre pour laquelle on recupere les reservations
	 * @return La liste des reservations associees a l'offre
	 */
	List<Reservation> getAllByIdOffre(@Param("id") Integer id);

}
