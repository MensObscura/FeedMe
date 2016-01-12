package fil.iagl.iir.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.adresse.AdresseDao;
import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.dao.image.ImageDao;
import fil.iagl.iir.dao.message.MessageDao;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.dao.vote.VoteDao;
import fil.iagl.iir.service.impl.AdresseServiceImpl;
import fil.iagl.iir.service.impl.AuthentificationServiceImpl;
import fil.iagl.iir.service.impl.ImageServiceImpl;
import fil.iagl.iir.service.impl.MessageServiceImpl;
import fil.iagl.iir.service.impl.OffreServiceImpl;
import fil.iagl.iir.service.impl.ReservationServiceImpl;
import fil.iagl.iir.service.impl.UtilisateurServiceImpl;
import fil.iagl.iir.service.impl.VoteServiceImpl;

public abstract class AbstractServiceTest extends AbstractFeedMeTest {
  @InjectMocks
  protected AuthentificationServiceImpl authentificationService;

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

  @InjectMocks
  protected MessageServiceImpl messageService;

  @InjectMocks
  protected VoteServiceImpl voteService;

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

  @Mock
  protected MessageDao messageDao;

  @Mock
  protected VoteDao voteDao;

}
