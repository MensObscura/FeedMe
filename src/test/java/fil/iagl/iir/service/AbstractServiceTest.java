package fil.iagl.iir.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.adresse.AdresseDao;
import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.service.impl.AuthentificationServiceImpl;
import fil.iagl.iir.service.impl.OffreServiceImpl;
import fil.iagl.iir.service.impl.ReservationServiceImpl;
import fil.iagl.iir.service.impl.UtilisateurServiceImpl;

public abstract class AbstractServiceTest extends AbstractFeedMeTest {

	@InjectMocks
	protected AuthentificationServiceImpl authentificationService;

	@InjectMocks
	protected UtilisateurServiceImpl utilisateurService;

	@InjectMocks
	protected ReservationServiceImpl reservationService;

	@InjectMocks
	protected OffreServiceImpl offreService;

	@Mock
	protected AuthentificationDao authentificationDao;

	@Mock
	protected UtilisateurDao utilisateurDao;

	@Mock
	protected ParticulierDao particulierDao;

	@Mock
	protected ReservationDao reservationDao;

	@Mock
	protected OffreDao offreDao;

	@Mock
	protected AdresseDao adresseDao;

	@Mock
	protected VilleDao villeDao;

}
