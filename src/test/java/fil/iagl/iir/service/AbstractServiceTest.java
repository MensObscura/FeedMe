package fil.iagl.iir.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.adresse.AdresseDao;
import fil.iagl.iir.dao.authentification.AuthentificationDao;
<<<<<<< HEAD
import fil.iagl.iir.dao.image.ImageDao;
=======
>>>>>>> 9cdbe9d8751994dbe6553f5dd06ff2cc3180f0ae
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.service.impl.AdresseServiceImpl;
import fil.iagl.iir.service.impl.AuthentificationServiceImpl;
<<<<<<< HEAD
import fil.iagl.iir.service.impl.ImageServiceImpl;
=======
>>>>>>> 9cdbe9d8751994dbe6553f5dd06ff2cc3180f0ae
import fil.iagl.iir.service.impl.OffreServiceImpl;
import fil.iagl.iir.service.impl.ReservationServiceImpl;
import fil.iagl.iir.service.impl.UtilisateurServiceImpl;

public abstract class AbstractServiceTest extends AbstractFeedMeTest {

  @InjectMocks
  protected AuthentificationServiceImpl authentificationService;
<<<<<<< HEAD

  @InjectMocks
  protected UtilisateurServiceImpl utilisateurService;

  @InjectMocks
  protected ReservationServiceImpl reservationService;

  @InjectMocks
  protected OffreServiceImpl offreService;

  @InjectMocks
  protected AdresseServiceImpl adresseService;

  @InjectMocks
  protected ImageServiceImpl imageService;

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

  @Mock
  protected AdresseService adresseServiceMock;

  @Mock
  protected ImageDao imageDao;
=======

  @InjectMocks
  protected UtilisateurServiceImpl utilisateurService;

  @InjectMocks
  protected ReservationServiceImpl reservationService;

  @InjectMocks
  protected OffreServiceImpl offreService;

  @InjectMocks
  protected AdresseServiceImpl adresseService;

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

  @Mock
  protected AdresseService adresseServiceMock;
>>>>>>> 9cdbe9d8751994dbe6553f5dd06ff2cc3180f0ae

}
