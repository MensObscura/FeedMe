package fil.iagl.iir.dao;

import java.time.LocalDate;
import java.time.Month;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.SQLCODE;

public class ReservationDaoTest extends AbstractDaoTest {

	private static final int NB_RESERVATION_OFFRE_ID_1 = 1;

	@Test
	public void sauvegarderTestSucces() throws Exception {
		Integer idOffre = 1;
		Integer idConvive = 2;
		LocalDate dateReservation = LocalDate.of(2015, Month.JANUARY, 15);

		Offre offre = new Offre();
		offre.setId(idOffre);

		Utilisateur convive = new Utilisateur();
		convive.setIdUtilisateur(idConvive);

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		this.reservationDao.sauvegarder(reservation);

		Assertions.assertThat(reservation.getId()).isNotNull().isPositive();
		Assertions.assertThat(reservation.getConvive()).isNotNull();
		Assertions.assertThat(reservation.getConvive().getIdUtilisateur()).isNotNull().isEqualTo(idConvive);
		Assertions.assertThat(reservation.getOffre()).isNotNull();
		Assertions.assertThat(reservation.getOffre().getId()).isNotNull().isEqualTo(idOffre);
		Assertions.assertThat(reservation.getDateReservation()).isNotNull().isEqualTo(dateReservation);
	}

	@Test
	public void sauvegarderTestEchec_OffreNull() throws Exception {
		Integer idConvive = 2;
		LocalDate dateReservation = LocalDate.of(2015, Month.JANUARY, 15);

		Offre offre = null;

		Utilisateur convive = new Utilisateur();
		convive.setIdUtilisateur(idConvive);

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		try {
			this.reservationDao.sauvegarder(reservation);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_ConviveNull() throws Exception {
		Integer idOffre = 1;
		LocalDate dateReservation = LocalDate.of(2015, Month.JANUARY, 15);

		Offre offre = new Offre();
		offre.setId(idOffre);

		Utilisateur convive = null;

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		try {
			this.reservationDao.sauvegarder(reservation);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_DateReservationNull() throws Exception {
		Integer idOffre = 1;
		Integer idConvive = 2;
		LocalDate dateReservation = null;

		Offre offre = new Offre();
		offre.setId(idOffre);

		Utilisateur convive = new Utilisateur();
		convive.setIdUtilisateur(idConvive);

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		try {
			this.reservationDao.sauvegarder(reservation);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_OffreNonExistant() throws Exception {
		Integer idOffre = Integer.MAX_VALUE;
		Integer idConvive = 2;
		LocalDate dateReservation = LocalDate.of(2015, Month.JANUARY, 15);

		Offre offre = new Offre();
		offre.setId(idOffre);

		Utilisateur convive = new Utilisateur();
		convive.setIdUtilisateur(idConvive);

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		try {
			this.reservationDao.sauvegarder(reservation);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_ConviveNonExistant() throws Exception {
		Integer idOffre = 1;
		Integer idConvive = Integer.MAX_VALUE;
		LocalDate dateReservation = LocalDate.of(2015, Month.JANUARY, 15);

		Offre offre = new Offre();
		offre.setId(idOffre);

		Utilisateur convive = new Utilisateur();
		convive.setIdUtilisateur(idConvive);

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		try {
			this.reservationDao.sauvegarder(reservation);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_TupleUnique() throws Exception {
		Integer idOffre = 1;
		Integer idConvive = 3;
		LocalDate dateReservation = LocalDate.of(2015, Month.JANUARY, 15);

		Offre offre = new Offre();
		offre.setId(idOffre);

		Utilisateur convive = new Utilisateur();
		convive.setIdUtilisateur(idConvive);

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		try {
			this.reservationDao.sauvegarder(reservation);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.UNIQUE_VIOLATION);
		}
	}

	@Test
	public void getAllByIdOffreTestSucces() throws Exception {
		Integer idOffre = 1;
		Assertions.assertThat(this.reservationDao.getAllByIdOffre(idOffre)).isNotEmpty()
				.hasSize(NB_RESERVATION_OFFRE_ID_1);
	}

	@Test
	public void getAllByIdOffreTestEchec() throws Exception {
		Assertions.assertThat(this.reservationDao.getAllByIdOffre(null)).isEmpty();
		Assertions.assertThat(this.reservationDao.getAllByIdOffre(Integer.MAX_VALUE)).isEmpty();
	}
}
