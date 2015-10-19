package fil.iagl.iir.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.SQLCODE;

public class ReservationDaoTest extends AbstractDaoTest {

	private static final Integer OFFRE_ID_DEFAULT = 1;
	private static final Integer CONVIVE_ID_DEFAULT = 2;
	private static final LocalDate DATE_DEFAULT = LocalDate.of(2015, Month.JANUARY, 15);

	private static final Integer OFFRE_ID_4 = 4;
	private static final Integer NB_RESERVATION_OFFRE_ID_4 = 2;

	@Test
	public void sauvegarderTestSucces() throws Exception {
		// Etant donne une reservation associee a une offre et a un convive
		Reservation reservation = buildReservation(OFFRE_ID_DEFAULT, CONVIVE_ID_DEFAULT, DATE_DEFAULT);

		// Quand on enregistre la reservation
		this.reservationDao.sauvegarder(reservation);

		// Alors on verifie que l'ID de la reservation a bien ete genere
		Assertions.assertThat(reservation.getId()).isNotNull().isPositive();
		// et que les informations sont conformes
		Assertions.assertThat(reservation.getConvive()).isNotNull();
		Assertions.assertThat(reservation.getConvive().getIdUtilisateur()).isNotNull().isEqualTo(CONVIVE_ID_DEFAULT);
		Assertions.assertThat(reservation.getOffre()).isNotNull();
		Assertions.assertThat(reservation.getOffre().getId()).isNotNull().isEqualTo(OFFRE_ID_DEFAULT);
		Assertions.assertThat(reservation.getDateReservation()).isNotNull().isEqualTo(DATE_DEFAULT);
	}

	@Test
	public void sauvegarderTestEchec_OffreNull() throws Exception {
		// Etant donne une reservation
		Reservation reservation = buildReservation(null, CONVIVE_ID_DEFAULT, DATE_DEFAULT);
		// qui n'est associee a aucune offre
		reservation.setOffre(null);

		try {
			// Quand on enregistre cette reservation en base
			this.reservationDao.sauvegarder(reservation);

			// Alors on attend a ce qu'une exception soit lancee
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_ConviveNull() throws Exception {
		// Etant donne une reservation
		Reservation reservation = buildReservation(OFFRE_ID_DEFAULT, null, DATE_DEFAULT);
		// qui n'est associee a aucun convive
		reservation.setConvive(null);

		try {
			// Quand on enregistre cette reservation en base
			this.reservationDao.sauvegarder(reservation);

			// Alors on attend a ce qu'une exception soit lancee
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_DateReservationNull() throws Exception {
		// Etant donne une reservation n'ayant aucune date de reservation
		Reservation reservation = buildReservation(OFFRE_ID_DEFAULT, CONVIVE_ID_DEFAULT, null);

		try {
			// Quand on enregistre cette reservation en base
			this.reservationDao.sauvegarder(reservation);

			// Alors on attend a ce qu'une exception soit lancee
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_OffreNonExistant() throws Exception {
		// Etant donne une reservation associee a une offre qui n'existe pas en
		// base
		Reservation reservation = buildReservation(Integer.MAX_VALUE, CONVIVE_ID_DEFAULT, DATE_DEFAULT);

		try {
			// Quand on enregistre cette reservation en base
			this.reservationDao.sauvegarder(reservation);

			// Alors on attend a ce qu'une exception soit lancee
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_ConviveNonExistant() throws Exception {
		// Etant donne une reservation associee a un convive qui n'existe pas en
		// base
		Reservation reservation = buildReservation(OFFRE_ID_DEFAULT, Integer.MAX_VALUE, DATE_DEFAULT);

		try {
			// Quand on enregistre cette reservation en base
			this.reservationDao.sauvegarder(reservation);

			// Alors on attend a ce qu'une exception soit lancee
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_TupleUnique() throws Exception {
		Integer idConvive = 1;

		// Etant donne une reservation qui existe deja en base
		Reservation reservation = buildReservation(OFFRE_ID_DEFAULT, idConvive, DATE_DEFAULT);

		try {
			// Quand on enregistre cette reservation en base
			this.reservationDao.sauvegarder(reservation);

			// Alors on attend a ce qu'une exception soit lancee
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.UNIQUE_VIOLATION);
		}
	}

	@Test
	public void getAllByIdOffreTestSucces() throws Exception {
		// Etant donne qu'il existe plusieurs reservations pour une offre en
		// base
		// Quand on recupere la liste des reservations associee a l'offre 4
		List<Reservation> reservations = this.reservationDao.getAllByIdOffre(OFFRE_ID_4);

		// Alors on verifie le nombre de reservations retournees
		Assertions.assertThat(reservations).isNotEmpty().hasSize(NB_RESERVATION_OFFRE_ID_4);

		// et que pour chaque reservation, le bon ID d'offre est renseigne
		Assertions.assertThat(reservations).are(new Condition<Reservation>() {
			@Override
			public boolean matches(Reservation reseration) {
				return reseration.getOffre().getId().equals(OFFRE_ID_4);
			}
		});
	}

	@Test
	public void getAllByIdOffreTestEchec() throws Exception {
		// Etant donne qu'aucune reservation n'est enregistree avec un ID nul
		// Quand on recupere une reservation avec un ID nul
		// Alors la reservation retournee est nulle
		Assertions.assertThat(this.reservationDao.getAllByIdOffre(null)).isEmpty();

		// Etant donne qu'aucune reservation n'est enregistree avec cet ID
		// Quand on recupere une reservation avec cet ID
		// Alors la reservation retournee est nulle
		Assertions.assertThat(this.reservationDao.getAllByIdOffre(Integer.MAX_VALUE)).isEmpty();
	}

	/* **************************** BUILDS ***********************************/

	public Reservation buildReservation(Integer idOffre, Integer idConvive, LocalDate dateReservation) {
		// Creation d'une offre
		Offre offre = new Offre();
		offre.setId(idOffre);

		// Creation d'un convive
		Utilisateur convive = new Utilisateur();
		convive.setIdUtilisateur(idConvive);

		Reservation reservation = new Reservation();
		reservation.setOffre(offre);
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);

		return reservation;
	}
}
