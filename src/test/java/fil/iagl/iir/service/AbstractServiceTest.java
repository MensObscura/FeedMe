package fil.iagl.iir.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.service.impl.UtilisateurServiceImpl;

public abstract class AbstractServiceTest extends AbstractFeedMeTest {

	@InjectMocks
	protected UtilisateurServiceImpl utilisateurService;

	@Mock
	protected UtilisateurDao utilisateurDao;

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

}
