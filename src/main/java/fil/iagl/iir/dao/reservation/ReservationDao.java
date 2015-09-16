package fil.iagl.iir.dao.reservation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Reservation;

public interface ReservationDao {

	Integer sauvegarder(@Param("reservation") Reservation reservation);

	List<Reservation> getAllByIdOffre(@Param("id") Integer id);

}
