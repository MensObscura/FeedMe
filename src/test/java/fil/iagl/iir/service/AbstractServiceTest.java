package fil.iagl.iir.service;

import java.time.LocalDate;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.service.impl.OffreServiceImpl;
import fil.iagl.iir.service.impl.ReservationServiceImpl;
import fil.iagl.iir.service.impl.UtilisateurServiceImpl;

public abstract class AbstractServiceTest extends AbstractFeedMeTest {

	@InjectMocks
	protected UtilisateurServiceImpl utilisateurService;

	@InjectMocks
	protected ReservationServiceImpl reservationService;

	@InjectMocks
	protected OffreServiceImpl offreService;

	@Mock
	protected UtilisateurDao utilisateurDao;

	@Mock
	protected ReservationDao reservationDao;

	@Mock
	protected OffreDao offreDao;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	protected Utilisateur createUtilisateur() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(1);
		utilisateur.setMail("toto.toto@gmail.com");
		utilisateur.setNom("toto");
		return utilisateur;
	}

	protected Reservation createReservation() {
		Integer idOffre = 1;

		Utilisateur convive = new Utilisateur();

		Offre offre = new Offre();
		offre.setId(idOffre);

		LocalDate dateReservation = LocalDate.now();

		Reservation reservation = new Reservation();
		reservation.setConvive(convive);
		reservation.setDateReservation(dateReservation);
		reservation.setOffre(offre);

		return reservation;
	}

}
