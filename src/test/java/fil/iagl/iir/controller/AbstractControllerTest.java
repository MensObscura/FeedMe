package fil.iagl.iir.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.typeCuisine.TypeCuisineDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.service.AuthentificationService;
import fil.iagl.iir.service.ImageService;
import fil.iagl.iir.service.OffreService;
import fil.iagl.iir.service.UtilisateurService;

public abstract class AbstractControllerTest extends AbstractFeedMeTest {

  protected MockMvc mockMvc;

  @Mock
  protected UtilisateurService utilisateurService;

  @Mock
  protected AuthentificationService authentificationService;

  @Mock
  protected OffreService offreService;

  @Mock
  protected OffreDao offreDao;

  @Mock
  protected UtilisateurDao utilisateurDao;

  @Mock
  protected VilleDao villeDao;

  @Mock
  protected ImageService imageService;

  @Mock
  protected TypeCuisineDao typeCuisineDao;

  @Mock
  protected AuthentificationDao authentificationDao;

  @InjectMocks
  protected UtilisateurController utilisateurController;

  @InjectMocks
  protected ReservationController reservationController;

  @InjectMocks
  protected ParametrageController parametrageController;

  @InjectMocks
  protected OffreController offreController;

  @InjectMocks
  protected ImageController imageController;

  @Autowired
  protected WebApplicationContext wac;

}
