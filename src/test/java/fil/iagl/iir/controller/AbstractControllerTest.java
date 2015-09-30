package fil.iagl.iir.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.service.AuthentificationService;
import fil.iagl.iir.service.UtilisateurService;

public abstract class AbstractControllerTest extends AbstractFeedMeTest {

	protected MockMvc mockMvc;

	@Mock
	protected UtilisateurService utilisateurService;

	@Mock
	protected AuthentificationService authentificationService;

	@Mock
	protected UtilisateurDao utilisateurDao;

	@Mock
	protected AuthentificationDao authentificationDao;

	@InjectMocks
	protected UtilisateurController utilisateurController;

	@Autowired
	protected WebApplicationContext wac;
}
